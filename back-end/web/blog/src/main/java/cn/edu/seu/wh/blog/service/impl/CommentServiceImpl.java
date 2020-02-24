/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.service.impl;

import cn.edu.seu.wh.blog.async.EventModel;
import cn.edu.seu.wh.blog.async.EventProducer;
import cn.edu.seu.wh.blog.async.EventType;
import cn.edu.seu.wh.blog.dao.*;
import cn.edu.seu.wh.blog.jedis.JedisClient;
import cn.edu.seu.wh.blog.model.ArticleViewInfo;
import cn.edu.seu.wh.blog.model.ArticleWeight;
import cn.edu.seu.wh.blog.model.HostHolder;
import cn.edu.seu.wh.blog.model.comment.Comment;
import cn.edu.seu.wh.blog.model.comment.CommentChildren;
import cn.edu.seu.wh.blog.model.comment.CommentRecive;
import cn.edu.seu.wh.blog.model.comment.CommentSend;
import cn.edu.seu.wh.blog.result.ResultInfo;
import cn.edu.seu.wh.blog.service.CommentService;
import cn.edu.seu.wh.blog.utils.RedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ArticleInfoDao articleInfoDao;
    @Autowired
    ArticleViewInfoDao articleViewInfoDao;
    @Autowired
    ArticleWeightDao articleWeightDao;
    @Autowired
    JedisClient jedisClient;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    EventProducer eventProducer;

    @Override
    public ResultInfo publish(CommentRecive commentRecive) {
        Comment comment=new Comment();
        CommentSend commentSend=new CommentSend();
        CommentChildren commentChildren=new CommentChildren();
        comment.setArticleid(commentRecive.getArticleid());
        comment.setAuthorid(commentRecive.getAuthorid());
        comment.setContent(commentRecive.getContent());
        comment.setLikecount(0);
        comment.setCreatedate(new Date());
        comment.setAtlevel(0);//对文章进行评论
        ArticleViewInfo articleViewInfo=null;
        ArticleWeight articleWeight=null;
        if(commentRecive.getParentid()==null){
            //对文章评论
            commentDao.AddComment(comment);
            //将评论数写入redis
            String commentkey= RedisKeyUtils.getCommentKey(comment.getArticleid());
            jedisClient.sadd(commentkey,String.valueOf(comment.getId()));

            //计算文章热度
            EventModel hotEventModel=new EventModel();
            hotEventModel.setEntityid(comment.getArticleid());
            hotEventModel.setAuthorid(comment.getAuthorid());
            hotEventModel.setEventType(EventType.WEIGHT);
            eventProducer.fireEvent(hotEventModel);

            //评论异步系统消息
            EventModel commentEventModel=new EventModel();
            commentEventModel.setEventType(EventType.COMMENT);
            commentEventModel.setEntityid(comment.getArticleid());
            commentEventModel.setEntityownerid(articleInfoDao.selectByid(commentEventModel.getEntityid()).getAuthor());
            commentEventModel.setAuthorid(comment.getAuthorid());
            eventProducer.fireEvent(commentEventModel);

            commentSend.setAuthor(userDao.selectById(comment.getAuthorid()));
            commentSend.setChildrens(null);
            commentSend.setAtlevel(0);
            commentSend.setArticleInfo(articleInfoDao.selectByid(comment.getArticleid()));
            commentSend.setContent(comment.getContent());
            commentSend.setCreatedate(comment.getCreatedate());
            commentSend.setLikecount(comment.getLikecount());
            commentSend.setId(comment.getId());


            return ResultInfo.ok(commentSend);
        }else{
            comment.setParentid(commentRecive.getParentid());
            if(commentRecive.getToid()==null){
                //对评论进行评论
                comment.setAtlevel(1);
                commentChildren.setAtlevel(comment.getAtlevel());
                commentDao.AddComment(comment);
            }
            else{
                comment.setAtlevel(2);
                comment.setToid(commentRecive.getToid());
                commentDao.AddComment(comment);
                commentChildren.setAtlevel(comment.getAtlevel());
                commentChildren.setToComment(commentDao.SelectById(comment.getToid()));
                commentChildren.setToUser(userDao.selectById(commentChildren.getToComment().getAuthorid()));
            }
            commentChildren.setArticleid(comment.getId());
            commentChildren.setAtlevel(comment.getAtlevel());
            commentChildren.setAuthor(userDao.selectById(comment.getAuthorid()));
            commentChildren.setContent(comment.getContent());
            commentChildren.setCreatedate(comment.getCreatedate());
            commentChildren.setId(comment.getId());
            commentChildren.setLikecount(comment.getLikecount());
            commentChildren.setParentcomment(commentDao.SelectById(comment.getParentid()));
            return ResultInfo.ok(commentChildren);

        }



    }


    @Override
    public ResultInfo getCommentByArticleId(Integer id) {
        List<Comment> commentList=commentDao.SelectByArticleId(id);
        List<CommentSend> commentSendList=new ArrayList<CommentSend>();
        CommentSend commentSend=null;
        List<Comment> commentstemp=null;
        CommentChildren commentChildren=null;
        Integer userid=hostHolder.getUser().getId();
        List<CommentChildren> commentChildrenList=null;
        for(Comment item:commentList){
            commentSend=new CommentSend();
            commentChildrenList=new ArrayList<>();
            commentSend.setId(item.getId());
            commentSend.setLikecount(item.getLikecount());
            commentSend.setCreatedate(item.getCreatedate());
            commentSend.setContent(item.getContent());
            commentSend.setAtlevel(item.getAtlevel());
            commentSend.setArticleInfo(articleInfoDao.selectByid(item.getArticleid()));
            String likekey=RedisKeyUtils.getCommentLikeKey(item.getId());
            commentSend.setLikecount(jedisClient.scard(likekey));
            commentSend.setIslike(jedisClient.sismember(likekey,String.valueOf(userid)));
            commentSend.setAuthor(userDao.selectById(item.getAuthorid()));
            commentstemp=commentDao.SelectChildren(item.getId());
            for(Comment temp:commentstemp){
                commentChildren=new CommentChildren();
                commentChildren.setArticleid(temp.getId());
                commentChildren.setAtlevel(temp.getAtlevel());
                commentChildren.setAuthor(userDao.selectById(temp.getAuthorid()));
                commentChildren.setContent(temp.getContent());
                commentChildren.setCreatedate(temp.getCreatedate());
                commentChildren.setId(temp.getId());
                String likekeychild=RedisKeyUtils.getCommentLikeKey(temp.getId());
                commentChildren.setLikecount(jedisClient.scard(likekeychild));
                commentChildren.setIslike(jedisClient.sismember(likekeychild,String.valueOf(userid)));
                if(temp.getToid()!=null){
                    commentChildren.setToComment(commentDao.SelectById(temp.getToid()));
                    commentChildren.setToUser(userDao.selectById(commentChildren.getToComment().getAuthorid()));
                }
                commentChildren.setParentcomment(item);
                commentChildrenList.add(commentChildren);
            }
            commentSend.setChildrens(commentChildrenList);
            commentSendList.add(commentSend);
        }


        return ResultInfo.ok(commentSendList);
    }


    /*
     * 评论点赞或取消点赞
     * json格式：
     * type：1 点赞 2 取消点赞
     * entityid:评论的id
     * userid:点赞用户id
     * */
    @Override
    public ResultInfo commentLike(Integer type, Integer entityid, Integer userid) {

        if(type==1){
            String likekey= RedisKeyUtils.getCommentLikeKey(entityid);//static方法不需要new
            jedisClient.sadd(likekey,String.valueOf(userid));
            return ResultInfo.ok(jedisClient.scard(likekey));
        }
        if(type==2){
            //取消点赞
            String likekey=RedisKeyUtils.getCommentLikeKey(entityid);
            jedisClient.srem(likekey,String.valueOf(userid));
            return ResultInfo.ok(jedisClient.scard(likekey));
        }
        return null;
    }
}

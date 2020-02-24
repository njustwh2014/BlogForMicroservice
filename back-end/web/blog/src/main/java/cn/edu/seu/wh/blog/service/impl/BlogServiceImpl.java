/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.service.impl;

import cn.edu.seu.wh.blog.dao.BlogCommentDao;
import cn.edu.seu.wh.blog.dao.BlogDao;
import cn.edu.seu.wh.blog.dao.UserDao;
import cn.edu.seu.wh.blog.model.Blog;
import cn.edu.seu.wh.blog.model.BlogComment;
import cn.edu.seu.wh.blog.model.User;
import cn.edu.seu.wh.blog.result.ResultInfo;
import cn.edu.seu.wh.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogDao blogDao;
    @Autowired
    UserDao userDao;
    @Autowired
    BlogCommentDao blogCommentDao;
    @Override
    public ResultInfo publish(String title, String content, String keyword, Integer flag, Integer userid, Boolean status) {
        if(StringUtils.isBlank(title)||StringUtils.isBlank(content)){
            return ResultInfo.build(400,"标题或内容为空");
        }
        Date date=new Date();
        Blog blog=new Blog();
        User author=new User();
        author=userDao.selectById(userid);
        blog.setContent(content);
        blog.setTitle(title);
        blog.setFlag(flag);
        blog.setUserid(userid);
        blog.setNickname(author.getNickname());
        blog.setKeyword(keyword);
        blog.setStatus(status);
        blog.setUserid(userid);
        blog.setHeadportraiturl(author.getHeadportraiturl());
        blog.setLikeCount(0);
        blog.setDislikeCount(0);
        blog.setViewCount(0);
        blog.setPublishTime(date);
        blog.setUpdateTime(date);
        blog.setCommentCount(0);//后期根据数据库来
        blog.setEntityType(0);
        blog.setHotScore(0);
        blogDao.addBlog(blog);
        return ResultInfo.ok(blog.getId());
    }

    @Override
    public ResultInfo onload(Integer flag, Integer start, Integer num) {
        List<Blog> data=null;
        if(flag==0){
            data=blogDao.findByid(start,start+num);
        }
        else{
            data=blogDao.findByid_flag(flag,start,start+num);
        }
        return ResultInfo.build(200,String.valueOf(data.size()),data);
//        data=blogDao.find();
//        return ResultInfo.build(200,String.valueOf(data.size()),data);
    }

    @Override
    public ResultInfo onLoadHot(Integer begin, Integer end) {
        List<Blog> data=null;
        data=blogDao.findHotBlog();
//        data=blogDao.find();
        return ResultInfo.ok(data);

    }

    @Override
    public ResultInfo onLoadDetail(Integer id) {
        Blog data=null;
        data=blogDao.SelectByid(id);
        if(data==null){
            return ResultInfo.build(400,"该博客不存在",data);
        }
        else{
            return ResultInfo.ok(data);
        }
    }

    @Override
    public ResultInfo loadBlogComment(Integer entityId, Integer entityType, Integer num, Integer start) {
        List<BlogComment> data=null;
        List<BlogComment> blogCommentReplyList=null;
        BlogComment blogComment=null;
        data=blogCommentDao.findByEntityId(entityId,start,num,entityType);
        if(data==null){
            return ResultInfo.build(999,"end",data);
        }
        else{
            for(Integer i=0;i<data.size();i++){
                blogComment=data.get(i);
                blogCommentReplyList=blogCommentDao.findReplyByEntityId(blogComment.getId());
                blogComment.setBlogCommentReplyList(blogCommentReplyList);
                data.set(i,blogComment);
            }
            return ResultInfo.build(200,String.valueOf(data.size()),data);
        }

    }

    @Override
    public ResultInfo comment(Integer entityId, Integer userId, Integer entityType, String content, Integer toCommentId) {
        if(StringUtils.isBlank(content)){
            return ResultInfo.build(400,"评论内容为空");
        }
        BlogComment blogComment=new BlogComment();
        Date date=new Date();
        User user=new User();
        Blog blog=new Blog();
        blogComment.setContent(content);
        blogComment.setCreateTime(date);
        blogComment.setDislikeCount(0);
        blogComment.setEntityId(entityId);
        blogComment.setLikeCount(0);
        blogComment.setReplyCount(0);
        blogComment.setEntityType(entityType);
        blogComment.setStatus(0);
        blogComment.setToCommentId(toCommentId);
        user=userDao.selectById(userId);
        blogComment.setHeadUrl(user.getHeadportraiturl());
        blogComment.setNickName(user.getNickname());
        blogComment.setUserId(userId);
        if(entityType==0){
            blog=blogDao.SelectByid(entityId);
            blog.setCommentCount(blog.getCommentCount()+1);
            blogDao.updateCommentCount(blog);
        }
        else{
            if(toCommentId!=0)
            {
                BlogComment blogComment1=blogCommentDao.SelectById(toCommentId);
                blogComment1.setReplyCount(blogComment1.getReplyCount()+1);
                blogCommentDao.updateReplyCount(blogComment1);
            }
        }
        blogCommentDao.addComment(blogComment);
        return ResultInfo.ok(blogComment.getId());
    }
}

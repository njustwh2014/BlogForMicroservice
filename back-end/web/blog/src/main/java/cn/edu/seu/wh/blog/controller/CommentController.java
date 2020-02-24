/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.controller;


import cn.edu.seu.wh.blog.model.comment.CommentRecive;
import cn.edu.seu.wh.blog.result.ResultInfo;
import cn.edu.seu.wh.blog.service.CommentService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(value="/comments")
public class CommentController {


    @Autowired
    CommentService commentService;
    //发表评论
    @RequestMapping(value = "/publish",method = RequestMethod.POST)
    public ResultInfo publish(@RequestBody CommentRecive commentRecive){
        return commentService.publish(commentRecive);
    }

    @RequestMapping(value="/article/{id}", method=RequestMethod.GET)
    public ResultInfo getCommentByArticle(@PathVariable Integer id){
        return commentService.getCommentByArticleId(id);
    }

    /*
    * 评论点赞或取消点赞
    * json格式：
    * type：1 点赞 2 取消点赞
    * entityid:评论的id
    * userid:点赞用户id
    * */
    @RequestMapping(value = "/like",method=RequestMethod.POST)
    public ResultInfo commentLike(@RequestBody JSONObject jsonObject){
        Integer type=jsonObject.getInteger("type");
        Integer entityid=jsonObject.getInteger("entityid");
        Integer userid=jsonObject.getInteger("userid");
        return commentService.commentLike(type,entityid,userid);
    }



}

/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.controller;

import cn.edu.seu.wh.blog.dao.BlogDao;
import cn.edu.seu.wh.blog.result.ResultInfo;
import cn.edu.seu.wh.blog.service.BlogService;
import cn.edu.seu.wh.blog.service.UploadService;
import cn.edu.seu.wh.blog.utils.FastDFSClient;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.shiro.authz.annotation.RequiresAuthentication;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(value = "/blog") //对于前端请求处理
public class BlogController {
    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
    @Autowired
    BlogDao blogDao;
    @Autowired
    BlogService blogService;
    @Autowired
    UploadService uploadService;
    // 发表博客
    @RequestMapping(value="/publish",method= RequestMethod.POST,produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultInfo publishBlog(@RequestBody JSONObject jsonObject){
        String title=jsonObject.getString("title");
        String content=jsonObject.getString("content");
        String keyword=jsonObject.getString("keyword");
        Boolean status=jsonObject.getBoolean("status");
        Integer flag=jsonObject.getInteger("flag");
        Integer userid=jsonObject.getInteger("userId");
        return blogService.publish(title,content,keyword,flag,userid,status);

    }

    //图片上传
    //接收图片的参数名需要为"editormd-image-file"
    @RequestMapping("/upload")
    @ResponseBody
    @RequiresAuthentication
    public JSONObject saveImage(@RequestParam("editormd-image-file") MultipartFile file) {
        return uploadService.saveImage(file);
    }
    /*加载blog
    * 前端json格式： start:需要加载页blog的第一个id，num加载页加载多少个blog,flag为0 时返回所有类型的blog
    *
    *
    * */
    @RequestMapping(value="/onLoad",method= RequestMethod.POST,produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultInfo onloadBlog(@RequestBody JSONObject jsonObject){
        Integer flag=jsonObject.getInteger("flag");
        Integer start=jsonObject.getInteger("start");
        Integer num=jsonObject.getInteger("num");
        return blogService.onload(flag,start,num);

    }

    /*
    * 加载热门博客
    * begin
    * end
    * */
    @RequestMapping(value="/onLoadHot",method= RequestMethod.POST,produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultInfo onloadHotBlog(@RequestBody JSONObject jsonObject){
        Integer begin=jsonObject.getInteger("begin");
        Integer end=jsonObject.getInteger("end");
        return blogService.onLoadHot(begin,end);
    }

    /*
    * 博客详情页
    * */
    @RequestMapping(value="/onLoadDetail",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  ResultInfo onLoadDetail(@RequestBody JSONObject jsonObject){
        Integer id=jsonObject.getInteger("blogId");
        return blogService.onLoadDetail(id);
    }

    /*
    * 加载博客评论
    *
    * */
    @RequestMapping(value = "/loadBlogComment",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  ResultInfo loadBlogComment(@RequestBody JSONObject jsonObject){
        Integer entityId=jsonObject.getInteger("entityId");
        Integer entityType=jsonObject.getInteger("entityType");
        Integer num=jsonObject.getInteger("num");
        Integer start=jsonObject.getInteger("start");
        return blogService.loadBlogComment(entityId,entityType,num,start);
    }

    /*
    * 发表评论
    * */
    @RequestMapping(value = "/comment",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultInfo comment(@RequestBody JSONObject jsonObject){
        String content=jsonObject.getString("content");
        Integer entityId=jsonObject.getInteger("entityId");
        Integer entityType=jsonObject.getInteger("entityType");
        Integer userId=jsonObject.getInteger("userId");
        Integer toCommentId=jsonObject.getInteger("toCommentId");
        return blogService.comment(entityId,userId,entityType,content,toCommentId);
    }



}

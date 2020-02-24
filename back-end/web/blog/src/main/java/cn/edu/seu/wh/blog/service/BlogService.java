/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.service;


import cn.edu.seu.wh.blog.result.ResultInfo;

public interface BlogService {
    public ResultInfo publish(String title, String content, String keyword, Integer flag, Integer userid, Boolean status);
    public ResultInfo onload(Integer flag,Integer start,Integer num);
    public ResultInfo onLoadHot(Integer begin,Integer end);
    public ResultInfo onLoadDetail(Integer id);
    public ResultInfo loadBlogComment(Integer entityId,Integer entityType,Integer num,Integer start);
    public ResultInfo comment(Integer entityId,Integer userId,Integer entityType,String content,Integer toCommentId);
}

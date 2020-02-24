/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.dao;


import cn.edu.seu.wh.blog.model.BlogComment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper
public interface BlogCommentDao {
    String TABLE_NAME=" blog_comment";
    String INSERT_FIELDS=" userId, nickName,headUrl,content,createTime,dislikeCount,likeCount,entityId,entityType,replyCount,status,toCommentId,toUserId,toNickName";

    @Insert({"insert into"+TABLE_NAME+"("+INSERT_FIELDS+")"+"values(#{userId},#{nickName},#{headUrl},#{content},#{createTime},#{dislikeCount},#{likeCount},#{entityId},#{entityType},#{replyCount},#{status},#{toCommentId},#{toUserId},#{toNickName})"})
    Integer addComment(BlogComment blogComment);

    @Select({"select * from",TABLE_NAME,"where entityId=#{entityId} and entityType=#{entityType} limit #{start},#{num} "})
    List<BlogComment> findByEntityId(@Param("entityId") Integer entityId, @Param("start") Integer start, @Param("num") Integer num, @Param("entityType") Integer entityType);

    @Select({"select * from",TABLE_NAME,"where toCommentId=#{commentId}"})
    List<BlogComment> findReplyByEntityId(@Param("commentId") Integer commentId);

    @Select({"select * from",TABLE_NAME,"where id=#{id}"})
    BlogComment SelectById(@Param("id") Integer id);

    @Update({"update ",TABLE_NAME, " set replyCount=#{replyCount} where id=#{id}"})
    void updateReplyCount(BlogComment blogComment);


}

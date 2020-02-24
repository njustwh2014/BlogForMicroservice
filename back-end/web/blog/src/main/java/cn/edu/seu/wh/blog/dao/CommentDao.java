/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.dao;

import cn.edu.seu.wh.blog.model.comment.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Mapper
public interface CommentDao {

    String TABLE_NAME=" articlecomment";
    String INSERT_FIELDS=" content,createdate,articleid,authorid,parentid,toid,atlevel,likecount";
    String SELECT_FIELDS="id, "+INSERT_FIELDS;

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values(#{content},#{createdate},#{articleid},#{authorid},#{parentid},#{toid},#{atlevel},#{likecount})"})
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int AddComment(Comment comment);

    @Select({"select * from",TABLE_NAME,"where id=#{id}"})
    Comment SelectById(Integer id);

    @Select({"select * from",TABLE_NAME,"where articleid=#{articleid} and atlevel=0 order by createdate desc "})
    List<Comment> SelectByArticleId(Integer articleid);

    @Select({"select * from",TABLE_NAME,"where parentid=#{parentid} order by createdate desc"})
    List<Comment> SelectChildren(Integer parentid);

    @Select({"select count(*) from",TABLE_NAME,"where articleid=#{articleid} and atlevel=0"})
    Integer CountComment(Integer articleid);
}

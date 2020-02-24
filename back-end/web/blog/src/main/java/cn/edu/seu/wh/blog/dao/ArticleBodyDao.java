/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.dao;

import cn.edu.seu.wh.blog.model.ArticleBody;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ArticleBodyDao {
    String TABLE_NAME=" articlebody ";
    String INSERT_FIELDS=" articleid,content,contenthtml";
    String SELECT_FIELDS="id, "+INSERT_FIELDS;

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values(#{articleid},#{content},#{contenthtml})"})
    int addArticleBody(ArticleBody articleBody);

    @Select({"select * from ",TABLE_NAME,"where articleid=#{articleid}"})
    ArticleBody selectByArticleId(Integer articleid);

    @Select({"update ",TABLE_NAME,"set content=#{content},contenthtml=#{contenthtml} where articleid=#{articleid}"})
    void updateArticleBodyByArticleId(ArticleBody articleBody);




}

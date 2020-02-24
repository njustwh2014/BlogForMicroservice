/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.dao;


import cn.edu.seu.wh.blog.model.ArticleViewInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;


@Component
@Mapper
public interface ArticleViewInfoDao {

    String TABLE_NAME=" articleviewinfo";
    String INSERT_FIELDS=" articleid,viewcount,commentcount";
    String SELECT_FIELDS="id, "+INSERT_FIELDS;

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values (#{articleid},#{viewcount},#{commentcount})"})
    int addArticleViewInfo(ArticleViewInfo articleViewInfo);

    @Update({"update ",TABLE_NAME, " set viewcount=#{viewcount},commentcount=#{commentcount} where articleid=#{articleid}"})
    void updateArticleViewInfo(ArticleViewInfo articleViewInfo);
    @Select({"select * from ",TABLE_NAME,"where articleid=#{articleid}"})
    ArticleViewInfo SelectByArticleID(Integer articleid);

    @Update({"update ",TABLE_NAME, " set viewcount=#{viewcount} where articleid=#{articleid}"})
    void updateArticleViewCount(ArticleViewInfo articleViewInfo);

    @Update({"update ",TABLE_NAME, " set commentcount=#{commentcount} where articleid=#{articleid}"})
    void updateArticleCommentCount(ArticleViewInfo articleViewInfo);
}

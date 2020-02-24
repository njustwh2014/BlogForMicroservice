/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.dao;


import cn.edu.seu.wh.blog.dao.provider.ArticleTagDaoProvider;
import cn.edu.seu.wh.blog.model.ArticleTag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper
public interface ArticleTagDao {
    String TABLE_NAME=" articletag";
    String INSERT_FIELDS=" articleid,tagid";
    String SELECT_FIELDS="id, "+INSERT_FIELDS;

//    @Insert({
//            "<script>",
//            "insert into",TABLE_NAME,"(",INSERT_FIELDS,") values ",
//            "<foreach collection='articleTagList' item='item' index='index' separator=','>",
//            "(#{item.articleid},#{item.tagid})",
//            "</foreach>",
//            "</script>"
//    })
//    int insertBatch(@Param(value="articleTagList")List<ArticleTag> articleTagList);

    @InsertProvider(type= ArticleTagDaoProvider.class,method = "addArticleTagBatch")
    void addArticleTagBatch(@Param("list") List<ArticleTag> articleTagList);

    @Select({"select * from ",TABLE_NAME,"where articleid=#{articleid}"})
    List<ArticleTag> SelectByArticleId(Integer articleid);

    @Delete({"delete from",TABLE_NAME,"where articleid=#{articleid}"})
    int deleteArticleTagByArticleId(int articleid);

}

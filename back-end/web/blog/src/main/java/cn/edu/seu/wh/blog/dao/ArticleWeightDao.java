/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.dao;


import cn.edu.seu.wh.blog.model.ArticleWeight;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface ArticleWeightDao {


    String TABLE_NAME=" articleweight";
    String INSERT_FIELDS=" articleid,weight";
    String SELECT_FIELDS="id, "+INSERT_FIELDS;

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values(#{articleid},#{weight})"})
    int addArticleWeight(ArticleWeight articleWeight);

    @Update({"update ",TABLE_NAME, " set weight=#{weight} where articleid=#{articleid}"})
    void updateArticleWeight(ArticleWeight articleWeight);

    @Select({"select * from",TABLE_NAME,"where articleid=#{articleid}"})
    ArticleWeight selectByArticleid(Integer articleid);

    @Select({"select t1.articleid,t1.weight,t2.title,t2.cover"+
            " from articleweight as t1 inner join articles as t2 on t1.articleid=t2.id and t2.articlestatus='show'"+
            " order by t1.weight desc limit 10"})
    List<Map<String,Object>> GetHotArticles();
}

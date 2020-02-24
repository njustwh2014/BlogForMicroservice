/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.dao;

import cn.edu.seu.wh.blog.model.ArticleCollectItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface ArticleCollectDao {
    /*
    * Table Name： articlecollect
    * 字段：
    * id int(11) not null primary key auto_increment,
      articleid int(11) not null,
      userid int(11) not null
    * */
    //添加收藏
    @Insert({"insert into articlecollect (userid,articleid) values(#{userid},#{articleid}) "})
    int addCollect(ArticleCollectItem articleCollectItem);

    //查某个收藏是否已存在
    @Select({"select id,userid,articleid from articlecollect where articleid=#{articleid} and userid=#{userid} limit 1"})
    Map<String,Object> selectCollect(@Param("userid")Integer userid, @Param("articleid")Integer articleid);

    //取消收藏
    @Delete({"delete from articlecollect where articleid=#{articleid} and userid=#{userid}"})
    int deleteCollect(@Param("userid")Integer userid,@Param("articleid")Integer articleid);


    //统计收藏量
    @Select({"select count(id) from articlecollect where articleid=#{articleid}"})
    Integer countCollectByArticleid(Integer articleid);

    //获取某个文章的收藏者
    @Select({"select userid from articlecollect where articleid=#{articleid}"})
    List<Map<String,Object>> getCollectorByArticleId(Integer id);
}

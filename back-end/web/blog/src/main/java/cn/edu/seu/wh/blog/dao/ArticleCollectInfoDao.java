/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.dao;


import cn.edu.seu.wh.blog.model.ArticleCollectInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ArticleCollectInfoDao {
    /*
    * Table Name： articlecollectinfo
    * 字段：
    * id int(11) not null primary key auto_increment,
      articleid int(11) not null,
      collectcount int(11) not null
    * */

    //创建记录
    @Insert({"insert into articlecollectinfo (articleid,collectcount) values (#{articleid},#{collectcount})"})
    int addCollectCountInfo(ArticleCollectInfo articleCollectInfo);

    //查找记录
    @Select({"select collectcount from articlecollectinfo where articleid=#{articleid} limit 1"})
    Integer getCollectCountByArticleId(Integer articleid);

    //更新记录
    @Update({"update articlecollectinfo set collectcount=#{collectcount} where articleid=#{articleid} limit 1"})
    int updateCollectCountInfo(ArticleCollectInfo articleCollectInfo);
}

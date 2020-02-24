/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface ArticleLinkTableDao {


    //获取首页文章简要信息
    /*
    status:
    msg:
    data:{
      list:[listItem...],
      listItem:{
        title,
        summary,
        publishtime,
        id,//article id
        author:{
          id,//user id
          nickname
        },
        commentcount,
        viewcount,
        tags:{//array
          id,
          articleid,
          tagdescription,
        },
        weight
      }

    }
    * */
    @Select({"select t1.id,t1.title,t1.summary,t1.publishtime,t1.author,t2.commentcount,t2.viewcount,t3.nickname,t3.id as userid"+
            " from "+
            " ((articles as t1 inner join articleviewinfo as t2 on t1.id=t2.articleid)"+
            " inner join user_information as t3 on t1.author=t3.id)"+
            " inner join category as t4 on t1.category=t4.id and t4.categorystatus='show' and t1.articlestatus='show'"+
            " order by t1.publishtime desc limit #{pageNumber},#{pageSize}"})
//    List<Map<String,Object>> GetAllArticle(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize);
    List<Map<String,Object>> GetAllArticle(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize);

    @Select({"select t1.id,t1.title,t1.summary,t1.publishtime,t1.author,t2.commentcount,t2.viewcount,t3.nickname,t3.id as userid"+
            " from "+
            " (articles as t1 inner join articleviewinfo as t2 on t1.id=t2.articleid and t1.category=#{id})"+
            " inner join user_information as t3 on t1.author=t3.id and t1.articlestatus='show'"+
            " order by t1.publishtime desc limit #{pageNumber},#{pageSize}"})
    List<Map<String, Object>> GetArticlesByCategory(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize, @Param("id") Integer id);

    @Select({"select t1.id,t1.title,t1.summary,t1.publishtime,t1.author,t2.commentcount,t2.viewcount,t3.nickname,t3.id as userid"+
            " from "+
            " ((articletag as t4 inner join articles as t1 on t1.id=t4.articleid and t4.tagid=#{id}) inner join articleviewinfo as t2 on t1.id=t2.articleid)"+
            " inner join user_information as t3 on t1.author=t3.id and t1.articlestatus='show' "+
            " order by t1.publishtime desc limit #{pageNumber},#{pageSize}"})
    List<Map<String, Object>>  GetArticlesByTag(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize, @Param("id") Integer id);

    @Select({"select t1.id,t1.title,t1.summary,t1.publishtime,t1.author,t2.commentcount,t2.viewcount,t3.nickname,t3.id as userid"+
            " from "+
            " ((articles as t1 inner join articlebody as t4 on t1.id=t4.articleid and (t1.title like CONCAT('%',#{keywords},'%') or t4.content like CONCAT('%',#{keywords},'%')))"+
            " inner join user_information as t3 on t1.author=t3.id)"+
            " inner join articleviewinfo as t2 on t1.id=t2.articleid and t1.articlestatus='show' "+
            " order by t1.publishtime desc limit #{pageNumber},#{pageSize}"})
    List<Map<String,Object>> getAllArticleByKeyWords(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize,@Param("keywords") String keywords);

    @Select({"select t1.id,t1.title,t2.commentcount,t2.viewcount,t1.summary,t1.author as authorid,t5.nickname,t5.headportraiturl as authoravtar,\n" +
            "t3.id as categoryid,t3.categorydescription,t1.publishtime,t4.content from \n" +
            "(((articles as t1 inner join articleviewinfo as t2 on t1.id=t2.articleid) \n" +
            "inner join category as t3 on t1.category=t3.id) \n" +
            "inner join articlebody as t4 on t1.id=t4.articleid) \n" +
            "inner join user_information as t5 on t1.author=t5.id and t1.id=#{id} limit 1;"})
    Map<String,Object> getArticleByid(Integer id);

    @Select({"select t1.id,t1.tagid,t2.tagdescription from articletag as t1 inner join tag as t2 on t1.tagid=t2.id and t1.articleid=#{articleid}"})
    List<Map<String,Object>> getTagByArticleid(Integer articleid);


}

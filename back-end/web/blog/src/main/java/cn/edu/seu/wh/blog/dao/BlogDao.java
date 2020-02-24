/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.dao;
import cn.edu.seu.wh.blog.model.Blog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Mapper
public interface BlogDao {
    String TABLE_NAME=" blog_information ";
    String INSERT_FIELDS=" user_id,nickname,title,content,keyword,flag,status,publishTime,updateTime,likeCount,dislikeCount,viewCount,recommended,headportraiturl,imageUrl,hotScore,commentCount,entityType,collectCount";
    String SELECT_FIELDS="id, "+INSERT_FIELDS;

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values(#{userid},#{nickname},#{title},#{content},#{keyword},#{flag},#{status},#{publishTime},#{updateTime},#{likeCount},#{dislikeCount},#{viewCount},#{recommended},#{headportraiturl},#{imageUrl},#{hotScore},#{commentCount},#{entityType},#{collectCount})"})
    int addBlog(Blog blog);

    //初级批量查
    @Select({"SELECT * FROM",TABLE_NAME})
    List<Blog> find();

//    @Select({"SELECT * FROM",TABLE_NAME,"WHERE 1=1"+
//            "<if test=&quot; id!=null &quot;>" +
//                    "AND id &lt; #{end}"+
//                    "AND id &gt; =#{start}"+
//            "</if>"
//            })
    @Select({"select * from",TABLE_NAME,"where id >= #{start} and id < #{end}"})
    List<Blog> findByid(@Param("start") Integer start, @Param("end") Integer end);
    @Select({"SELECT *FROM",TABLE_NAME,"where flag=#{flag} and id >= #{start} and id < #{end}"
    })
    List<Blog> findByid_flag(@Param("flag")Integer flag,@Param("start") Integer start,@Param("end") Integer end);

    @Select({"select * from",TABLE_NAME,"order by hotScore desc limit 10 "})
    List<Blog> findHotBlog();

    @Select({"select * from",TABLE_NAME,"where id=#{id}"})
    Blog SelectByid(Integer id);

    @Update({"update ",TABLE_NAME, " set commentCount=#{commentCount} where id=#{id}"})
    void updateCommentCount(Blog blog);
}
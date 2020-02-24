/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
@Mapper
public interface LinkTableQuery {

    String TABLE_NAME_1="articlebody";
    String TABLE_NAME_2="articles";
    String TABLE_NAME_3="articletag";
    String TABLE_NAME_4="articleviewinfo";
    String TABLE_NAME_5="user_information";

    @Select({"select t1.id,t1.author,t1.publishtime,t2.content from articles as t1 inner join articlebody as t2 on t1.id=t2.articleid"})
    List<HashMap<String,Object>> LinkTableQuery();
}
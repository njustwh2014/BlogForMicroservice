/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.dao;

import cn.edu.seu.wh.blog.model.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component
@Mapper

public interface TagDao {

    String TABLE_NAME=" tag ";
    String INSERT_FIELDS=" tagdescription";
    String SELECT_FIELDS="id, "+INSERT_FIELDS;

    @Select({"select * from"+TABLE_NAME})
    List<Tag> getAllTag();

    @Select({"select id,tagdescription from"+TABLE_NAME+"where categoryid=#{categoryid}"})
    List<Map<String,Object>> getTagByCategoryid(Integer categoryid);
}

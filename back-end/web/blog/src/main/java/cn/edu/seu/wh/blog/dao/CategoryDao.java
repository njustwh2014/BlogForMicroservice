/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.dao;


import cn.edu.seu.wh.blog.model.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Mapper

public interface CategoryDao {
    String TABLE_NAME=" category ";
    String INSERT_FIELDS=" categorydescription";
    String SELECT_FIELDS="id, "+INSERT_FIELDS;

    @Select({"select * from"+TABLE_NAME+"where categorystatus='show'"})
    List<Category> getAllCategory();

}

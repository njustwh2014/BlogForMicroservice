/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.dao;


import cn.edu.seu.wh.blog.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserDao {
    String TABLE_NAME=" user_information ";
    String INSERT_FIELDS="  account, password, registerstatus, token, salt, IPaddress,lasttime, " +
            "authority, nickname, gender, birthdate, school, hobby, headportraiturl, contributiondegree ";
    String SELECT_FIELDS="id, " + INSERT_FIELDS;


    @Insert({"insert into ",TABLE_NAME, "(", INSERT_FIELDS,
            ") values(#{account},#{password},#{registerstatus},#{token},#{salt},#{IPaddress}," +
                    "#{lasttime},#{authority},#{nickname},#{gender},#{birthdate},#{school}," +
                    "#{hobby},#{headportraiturl},#{contributiondegree})"})
    int addUser(User user);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    User selectById(int id);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where account=#{account}"})
    User selectByAccount(String account);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where token=#{token}"})
    User selectByToken(String token);


    @Update({"update ",TABLE_NAME, " set password=#{password} where id=#{id}"})
    void updatePassword(User user);

    @Update({"update ",TABLE_NAME, " set email=#{email} where id=#{id}"})
    void updateEmail(User user);

    @Update({"update ",TABLE_NAME, " set token=#{token} where id=#{id}"})
    void updateToken(User user);

    @Update({"update ",TABLE_NAME, " set headportraiturl=#{headportraitUrl} where id=#{id}"})
    void updateHeadUrl(User user);

    @Update({"update ",TABLE_NAME, " set nickname=#{nickname}, gender=#{gender},  " +
            "birthdate=#{birthdate},school=#{school},hobby=#{hobby},headportraiturl=#{headportraiturl} where id=#{id}" })
    void updateUserInfo(User user);
}

/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.service.impl;

import cn.edu.seu.wh.blog.dao.UserDao;
import cn.edu.seu.wh.blog.model.User;
import cn.edu.seu.wh.blog.result.ResultInfo;
import cn.edu.seu.wh.blog.service.UserService;
import cn.edu.seu.wh.blog.utils.Md5Utils;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {


    //session 的过期时间
    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    @Autowired
    UserDao userDao;




//    @Autowired
//    private JedisClient jedisClient;


    //邮箱重复性检查
    @Override
    public ResultInfo checkData(String email) {
        User user=null;
        user=userDao.selectByAccount(email);
        if(user==null){
            return ResultInfo.ok(false);
        }
        return ResultInfo.ok(true);
    }

    //用户注册
    @Override
    public ResultInfo register(String email, String password,String token) {
        if(StringUtils.isBlank(email)|| StringUtils.isBlank(password))
            return ResultInfo.build(400, "用户数据不完整，注册失败");

        User user=userDao.selectByAccount(email);
        if(user!=null){
            return ResultInfo.build(400,"此邮箱已经被注册");
        }

        Date date=new Date();
        user=new User();
        user.setNickname(email);
//        user.setHometown("江苏省 南京市");
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));//随机生成一段盐存入数据库
        user.setAccount(email);
        user.setPassword(Md5Utils.MD5(password + user.getSalt()));//存入密码加盐后的加密密文
        user.setLasttime(date);
        user.setToken(token);
        user.setHeadportraiturl("http://47.100.58.57:3000/7b185cce-adda-49a5-94bb-ddc870f0cd61_niming.jpg");
        user.setId(userDao.addUser(user));

//        return ResultInfo.ok(user.getId());
        return ResultInfo.ok(user);
    }

    //找回账户信息
    @Override
    public ResultInfo getbackAccount(String email,String password,String token){
        if(StringUtils.isBlank(email)|| StringUtils.isBlank(password))
            return ResultInfo.build(400, "用户数据不完整，注册失败");

        User user=userDao.selectByAccount(email);
        if(user==null){
            return ResultInfo.build(400,"此邮箱未被注册");
        }

        user.setPassword(Md5Utils.MD5(password + user.getSalt()));//存入密码加盐后的加密密文
        user.setToken(token);
        userDao.updatePassword(user);
        userDao.updateToken(user);
        return ResultInfo.ok(user.getId());
    }

    //登录
    @Override
    public ResultInfo login(String email, String password,String token) {
        User user=null;
        user=userDao.selectByAccount(email);
        System.out.println(user);
        if(user==null){
            return ResultInfo.build(400,"用户名或密码错误！");
        }
        if(!Md5Utils.MD5(password+user.getSalt()).equals(user.getPassword())){
            return ResultInfo.build(400,"用户名或密码错误！");
        }
        user.setToken(token);
        userDao.updateToken(user);
        return ResultInfo.ok(user);
    }

    @Override
    public ResultInfo getUserPhoto(String username, int type) {
        return null;
    }

    @Override
    public ResultInfo updateUserInfo(User user, String token) {
        return null;
    }

    //根据Token获取个人信息
    @Override
    public ResultInfo getUserByToken(String token) {

        User user=null;
        user=userDao.selectByToken(token);
        if(user==null) {
            return ResultInfo.build(400, "用户名或密码错误！");
        }
        return ResultInfo.ok(user);
    }

    @Override
    public ResultInfo getUserSettingByUserId(Integer userId) {
        return null;
    }

    //退出登录
    @Override
    public ResultInfo sinOut(String token) {
        User user=null;
        user=userDao.selectByToken(token);
        if(user==null) {
            return ResultInfo.build(400, "用户名或密码错误！");
        }
        else{
            user.setToken(null);
            userDao.updateToken(user);
            return ResultInfo.build(200,"退出登陆成功",user);
        }

    }

    @Override
    public ResultInfo getUserInfo(User user) {
        user=userDao.selectByToken(user.getToken());
        if(user==null) {
            return ResultInfo.build(400, "未登录，请登录！");
        }
        return ResultInfo.ok(user);
    }

    //更新密码
    @Override
    public ResultInfo updatePasswordById(Integer id, String password) {
        return null;
    }


    //检查密码的正确性
    @Override
    public ResultInfo checkPasswordById(Integer id, String password) {
        return null;
    }

    //更新邮箱
    @Override
    public ResultInfo updateEmail(Integer id, String password, String token) {
        return null;
    }

    @Override
    public ResultInfo updateBackgroundUrlById(User user, String token) {
        return null;
    }

    //更新头像
    @Override
    public ResultInfo updateHeadUrlById(User user, String token) {
        return null;
    }

    @Override
    public ResultInfo getUserInfoByUserId(Integer userId) {
        return null;
    }

    //更新个人信息

    @Override
    public ResultInfo updateInfo(JSONObject jsonObject) {
        User user=userDao.selectById(jsonObject.getInteger("userid"));
        if(user!=null){
            user.setHeadportraiturl(jsonObject.getString("headportraiturl"));
            user.setBirthdate(jsonObject.getDate("birthdate"));
            user.setHobby(jsonObject.getString("hobby"));
            user.setGender(jsonObject.getInteger("gender"));
            user.setNickname(jsonObject.getString("nickname"));
            user.setSchool(jsonObject.getString("school"));
            userDao.updateUserInfo(user);
            return ResultInfo.ok(user);
        }
        return ResultInfo.build(500,"failed",null);
    }

    @Override
    public User getUserInfoByUserIdAndReturnUSer(Integer id) {
        User user=userDao.selectById(id);
        return user;
    }
}
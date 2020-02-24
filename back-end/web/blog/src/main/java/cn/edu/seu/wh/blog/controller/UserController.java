/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.controller;


import cn.edu.seu.wh.blog.dao.UserDao;
import cn.edu.seu.wh.blog.model.HostHolder;
import cn.edu.seu.wh.blog.model.User;
import cn.edu.seu.wh.blog.result.ResultInfo;
import cn.edu.seu.wh.blog.service.UserService;
import cn.edu.seu.wh.blog.utils.EmailUtils;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(value = "/user") //对于前端请求处理
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private EmailUtils emailUtils;

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    /**
     * 用户名和邮箱重复性检查
     * 数据库中已有该用户名或者邮箱则返回true，否则返货false
     * @param jsonObject
     * @return
     */
    @RequestMapping(value="/check",method = RequestMethod.POST,
            produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultInfo checkData(@RequestBody JSONObject jsonObject){
        ResultInfo result=null;
        String email=jsonObject.getString("email");//获取用户注册时的邮箱进行后续验证
        try {
            result = userService.checkData(email);
        } catch (Exception e) {
            logger.error("重复性检查出现异常"+e.getMessage());
            e.printStackTrace();
            result=ResultInfo.build(500, "检查时服务器发生错误！");
        }
        return result;
    }

    @RequestMapping(value="/getUser",method = RequestMethod.POST,
            produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultInfo getUser(@RequestBody JSONObject jsonObject, HostHolder hostHolder){
        User user=hostHolder.getUser();
        ResultInfo result=null;
//        String email=jsonObject.getString("id");//获取用户注册时的邮箱进行后续验证
        try {
            if(user!=null){
                result = userService.getUserByToken(user.getToken());
            }
            else{
                //未登录
                result = userService.getUserByToken(null);
            }

        } catch (Exception e) {
            logger.error("根据token获取用户异常"+e.getMessage());
            e.printStackTrace();
            result=ResultInfo.build(500, "检查时服务器发生错误！");
        }
        return result;
    }


    //退出登陆
    @RequestMapping(value="/logout",method=RequestMethod.POST)
    public  ResultInfo logout(@RequestBody JSONObject jsonObject,HostHolder hostHolder){
        User user=hostHolder.getUser();
        ResultInfo result=null;
        try {
            result = userService.sinOut(user.getToken());
        } catch (Exception e) {
            logger.error("注销登陆失败"+e.getMessage());
            e.printStackTrace();
            result=ResultInfo.build(500, "检查时服务器发生错误！");
        }
        return result;
    }

    //邮箱注册
    @RequestMapping(value="/registeremail",method = RequestMethod.POST)
    public ResultInfo register(@RequestBody JSONObject jsonObject, HttpServletResponse response){
        String email=jsonObject.getString("email");
        String password=jsonObject.getString("password");

        //没有加是否需要记住登陆状态判断，直接给定一个有效期了
        String token= UUID.randomUUID().toString();
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        cookie.setMaxAge(3600 * 24 * 5);//5天的有效期
        response.addCookie(cookie);

        return userService.register(email,password,token);
    }

    //    //找回账户
    @RequestMapping(value="/getbackaccount",method=RequestMethod.POST)
    public ResultInfo getbackAccount(@RequestBody JSONObject jsonObject,HttpServletResponse response){
        String email=jsonObject.getString("email");
        String password=jsonObject.getString("password");
        //没有加是否需要记住登陆状态判断，直接给定一个有效期了
        String token= UUID.randomUUID().toString();
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        cookie.setMaxAge(3600 * 24 * 5);//5天的有效期
        response.addCookie(cookie);

        return userService.getbackAccount(email,password,token);

    }
    //注册时发送验证码
    /**
     * 发送验证码
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/captcha",method = RequestMethod.POST)
    public ResultInfo sendCaptcha(@RequestBody JSONObject jsonObject){
        ResultInfo result=null;
        String code=jsonObject.getString("code");
        String email=jsonObject.getString("email");

        try {
            result = emailUtils.sendCaptcha(code, email);
        } catch (Exception e) {
            logger.error("发送验证码时服务器发生异常"+e.getMessage());
            e.printStackTrace();
            result=ResultInfo.build(500, "发送验证码时服务器发生错误！");
        }
        return result;
    }

    //登录
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ResultInfo login(@RequestBody JSONObject jsonObject,HttpServletResponse response){
        ResultInfo result=null;
        String token= UUID.randomUUID().toString();
        String password=jsonObject.getString("password");
        String email=jsonObject.getString("email");
//        Boolean rememberme=jsonObject.getBoolean("rememberme");
        User user=userDao.selectByAccount(email);
        result=userService.login(email,password,token);

        if(result.getStatus()==200){
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            cookie.setMaxAge(3600 * 24 * 5);//5天的有效期
            response.addCookie(cookie);
        }

        return result;
    }
//    获取当前用户信息
    @RequestMapping(value = "/currentUser",method=RequestMethod.POST)
    public Object getUserInfo(@RequestBody JSONObject jsonObject,HostHolder hostHolder){
        String token=jsonObject.getString("token");
//        return userService.getUserInfo(user);
        return userService.getUserByToken(token);
    }
    //更新用户信息
    @RequestMapping(value="/updateInfo",method=RequestMethod.POST)
    public Object updateInfo(@RequestBody JSONObject jsonObject,HttpServletResponse response){

        return userService.updateInfo(jsonObject);

    }
}

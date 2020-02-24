/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.service;
import cn.edu.seu.wh.blog.model.User;
import cn.edu.seu.wh.blog.result.ResultInfo;
import com.alibaba.fastjson.JSONObject;

public interface UserService {
    public ResultInfo register(String email, String password, String token);//注册
    public ResultInfo checkData(String email);//邮箱重复性检查
    public ResultInfo login(String email, String password,String token);//登录
    public ResultInfo getbackAccount(String email,String password,String token);//找回账户信息
    public ResultInfo getUserPhoto(String username, int type);//获取用户头像
    public ResultInfo updateUserInfo(User user, String token);//更新用户个人信息
    public ResultInfo getUserByToken(String token);//根据Token获取用户信息
    public ResultInfo getUserSettingByUserId(Integer userId);
    public ResultInfo sinOut(String token);//退出登录
    public ResultInfo getUserInfo(User user);
    public ResultInfo updatePasswordById(Integer id,String password);//修改用户密码
    public ResultInfo checkPasswordById(Integer id,String password);//验证密码
    public ResultInfo updateEmail(Integer id,String password,String token);//更换邮箱
    public ResultInfo updateBackgroundUrlById(User user,String token);
    public ResultInfo updateHeadUrlById(User user,String token);//更新头像
    public ResultInfo getUserInfoByUserId(Integer userId);//获取个人信息
    public ResultInfo updateInfo(JSONObject jsonObject);
    public User getUserInfoByUserIdAndReturnUSer(Integer id);//获取个人信息
}

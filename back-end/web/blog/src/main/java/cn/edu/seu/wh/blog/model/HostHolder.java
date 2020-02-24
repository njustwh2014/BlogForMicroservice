/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.model;

import org.springframework.stereotype.Component;

@Component
public class HostHolder {
    //通过线程本地变量来表示，实现每个线程对该变量都有一个拷贝，即多个用户同时请求时不会冲突（变量副本）
    private static ThreadLocal<User> users=new ThreadLocal<User>();

    public User getUser(){
        return users.get();
    }

    public  void setUser(User user){
        users.set(user);
    }

    public void clear(){
        users.remove();
    }


}

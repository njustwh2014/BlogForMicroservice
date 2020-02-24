package cn.edu.seu.wh.blog.configuration;

import cn.edu.seu.wh.blog.interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program:blog
 * @description:配置拦截器 改用重写WebMvcConfiguer接口方法 替代弃用类WebMvcConfigurerAdapter
 * @author: Huan Wang(https://github.com/njustwh2014)
 * @create:2020-02-21 11:25
 **/
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Autowired
    PassportInterceptor passportInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passportInterceptor);//在系统初始化时把拦截器加入到整个链路上
    }
}

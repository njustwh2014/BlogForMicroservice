package cn.edu.seu.wh.blog.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program:blog
 * @description:跨域配置类 改用重写WebMvcConfiguer接口方法默认实现 替代弃用类WebMvcConfigurerAdapter
 * @author: Huan Wang(https://github.com/njustwh2014)
 * @create:2020-02-21 11:10
 **/
@Configuration
public class CrossDomainConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("*");
    }
}

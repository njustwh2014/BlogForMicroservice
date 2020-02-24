package cn.edu.seu.wh.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program:blog
 * @description:blog springboot Application类
 * @author: Huan Wang(https://github.com/njustwh2014)
 * @create:2020-02-21 11:41
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}

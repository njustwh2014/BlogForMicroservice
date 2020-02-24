package cn.edu.seu.wh.ftp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program:web
 * @description:文件上传服务
 * @author: Huan Wang(https://github.com/njustwh2014)
 * @create:2020-02-23 20:07
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class FTPApplication {
    public static void main(String[] args) {
        SpringApplication.run(FTPApplication.class,args);
    }
}

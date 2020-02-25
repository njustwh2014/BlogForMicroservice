package cn.edu.seu.wh.blog.service;

import com.alibaba.fastjson.JSONObject;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program:web
 * @description:文件上传服务
 * @author: Huan Wang(https://github.com/njustwh2014)
 * @create:2020-02-24 13:16
 **/
@FeignClient(name = "ftp-service",configuration = UploadService.MultipartSupportConfig.class)
public interface UploadService {

    /**
    * @Description: 保存图片   //接收图片的参数名需要为"editormd-image-file"
    * @Param: [image, request]
    * @return: com.alibaba.fastjson.JSONObject
    * @thorws:
    * @Author: Mr.Wang
    * @Date: 2020/2/24
    */
    @PostMapping(value = "/upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public JSONObject saveImage(@RequestPart(value = "editormd-image-file",required = false)MultipartFile image);

    @Scope("prototype")
    @Primary
    @Configuration
    class MultipartSupportConfig {
        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder();
        }
    }

}

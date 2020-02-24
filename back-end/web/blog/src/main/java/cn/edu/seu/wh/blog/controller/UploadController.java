/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.controller;

import cn.edu.seu.wh.blog.service.UploadService;
import com.alibaba.fastjson.JSONObject;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);


    @Autowired
    UploadService uploadService;


    /**
     * @Description: 保存图片   //接收图片的参数名需要为"editormd-image-file"
     * @Param: [image, request]
     * @return: com.alibaba.fastjson.JSONObject
     * @thorws:
     * @Author: Mr.Wang
     * @Date: 2020/2/24
     */
    @RequestMapping("/upload")
    @ResponseBody
    @RequiresAuthentication
    public JSONObject saveImage(@RequestParam("editormd-image-file") MultipartFile image){
        return uploadService.saveImage(image);
    }
}

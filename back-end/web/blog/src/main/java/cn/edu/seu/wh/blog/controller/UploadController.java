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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.UUID;


@RestController
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);


    @Value("F:/git_repository/VueSpringboot/images")
    private String baseFolderPath;

    @Value("${server.port}")
    private String port;

    @Value("http://192.168.132.130:3000")
    private String url;

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
    @RequestMapping(value = "/upload",method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    @RequiresAuthentication
    public JSONObject saveImage(@RequestPart(value = "editormd-image-file",required = false)MultipartFile image){
//        JSONObject jsonObject = new JSONObject();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        StringBuffer url = new StringBuffer();
//        File baseFolder = new File(baseFolderPath);
//        if (!baseFolder.exists()) {
//            baseFolder.mkdirs();
//        }
//        url.append(url);
//        String imgName = UUID.randomUUID() + "_" + image.getOriginalFilename().replaceAll(" ", "");
//        try {
//
//            File dest = new File(baseFolder, imgName);
//            image.transferTo(dest);
//            url.append("/").append(imgName);
//            jsonObject.put("url", url);//图片回显地址，即文件存放地址，应为虚拟路径
//            jsonObject.put("success", 1);//图片上传成功的信息码
//            jsonObject.put("message", "upload success!");//信息
//        } catch (IOException e) {
//            jsonObject.put("success", 0);//图片上传成功的信息码
//            jsonObject.put("message", "upload failed!");//信息
//        }
//        return jsonObject;
        return uploadService.saveImage(image);
    }
}

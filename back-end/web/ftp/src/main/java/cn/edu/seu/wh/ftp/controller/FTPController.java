package cn.edu.seu.wh.ftp.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * @program:web
 * @description:
 * @author: Huan Wang(https://github.com/njustwh2014)
 * @create:2020-02-24 10:58
 **/
@RestController
public class FTPController {

    @Value("${my.config.path}")
    private String baseFolderPath;

    @Value("${server.port}")
    private String port;

    @Value("${my.config.url}")
    private String url;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public JSONObject saveImage(@RequestPart(value = "editormd-image-file") MultipartFile image) {
        JSONObject jsonObject = new JSONObject();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        StringBuffer url = new StringBuffer();
        File baseFolder = new File(baseFolderPath);
        if (!baseFolder.exists()) {
            baseFolder.mkdirs();
        }
        url.append(url);
        String imgName = UUID.randomUUID() + "_" + image.getOriginalFilename().replaceAll(" ", "");
        try {

            File dest = new File(baseFolder, imgName);
            image.transferTo(dest);
            url.append("/").append(imgName);
            jsonObject.put("url", url);//图片回显地址，即文件存放地址，应为虚拟路径
            jsonObject.put("success", 1);//图片上传成功的信息码
            jsonObject.put("message", "upload success!");//信息
        } catch (IOException e) {
            jsonObject.put("success", 0);//图片上传成功的信息码
            jsonObject.put("message", "upload failed!");//信息
        }
        return jsonObject;
    }
}

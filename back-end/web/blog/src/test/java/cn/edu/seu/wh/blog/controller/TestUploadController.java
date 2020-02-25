package cn.edu.seu.wh.blog.controller;

import cn.edu.seu.wh.blog.service.UploadService;
import lombok.SneakyThrows;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @program:web
 * @description:
 * @author: Huan Wang(https://github.com/njustwh2014)
 * @create:2020-02-25 10:28
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestUploadController {

    @Autowired
    private UploadService uploadService;

    @Test
    @SneakyThrows
    public void testHandleFileUpload() {

        File file = new File("E:\\dogting.jpg");
        DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("editormd-image-file",
                MediaType.MULTIPART_FORM_DATA_VALUE, true, file.getName());

        try (
                InputStream input = new FileInputStream(file);
                OutputStream os = fileItem.getOutputStream()
        ) {
            IOUtils.copy(input,os);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file: " + e, e);
        }

        MultipartFile multi = new CommonsMultipartFile(fileItem);
        uploadService.saveImage(multi);

    }

}

package cn.edu.seu.wh.ftp.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.Collection;
import java.util.Map;

/**
 * @program:web
 * @description:
 * @author: Huan Wang(https://github.com/njustwh2014)
 * @create:2020-02-25 11:35
 **/
public class MyRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        Map<String, Collection<String>> map = requestTemplate.headers();
        System.out.println(map.get("content-type"));

        String boundary = Long.toHexString(System.currentTimeMillis());
        String contentTypeHeaderValue = new StringBuilder()
                .append("multipart/form-data")
                .append("; charset=UTF-8")
                .append("; boundary=").append(boundary)
                .toString();

        requestTemplate.header("content-type", contentTypeHeaderValue);
    }
}

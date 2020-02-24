/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.aspect;

import java.io.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    private final String filePath="F:/codeHub/myweblearnpath/seuwh_mstc/log/lo4j.txt";
//    private final String filePath="/home/huanhuan/myweb/lo4j.txt";//在服务器上地址
    @Before("execution(* seu.wh.seuwh_mstc.controller.*Controller.*(..))")
    public void beforeMethod(JoinPoint joinPoint) {
        StringBuilder sb = new StringBuilder();


        for (Object arg : joinPoint.getArgs()) {
            sb.append("arg:" + arg.toString() + "|");
        }
        logger.info("before method:" + sb.toString());
        WriteErrorToLog4J("before method:" + sb.toString());
    }

    @After("execution(* seu.wh.seuwh_mstc.controller.*Controller.*(..))")
    public void afterMethod() {
        logger.info("after method" + new Date());
        WriteErrorToLog4J("after method" + new Date());
    }

    //    把正常信息信息写进日志里面
    public void WriteErrorToLog4J(String message){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{

            FileWriter fw = new FileWriter(filePath, true);//如果追加方式用true
            BufferedWriter bw = new BufferedWriter(fw);
            String messageStr=new String(message.getBytes("utf-8"));

            bw.append("-----------"+sdf.format(new Date())+"------------\r\n");// 往已有的文件上添加字符串
            bw.append(messageStr+"\n ");

            bw.flush();
            bw.close();


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}


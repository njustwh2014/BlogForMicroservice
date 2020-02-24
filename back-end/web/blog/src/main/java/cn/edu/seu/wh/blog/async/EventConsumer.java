/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.async;

import cn.edu.seu.wh.blog.jedis.JedisClient;
import cn.edu.seu.wh.blog.utils.RedisKeyUtils;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//本类用于从队列中获取event，并选择相应的handler处理，处理事件要另起一个线程
@Service
public class EventConsumer implements InitializingBean, ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);
    @Autowired
    JedisClient jedisClient;

    private Map<EventType, List<EventHandler>> config=new HashMap<>();//建立eventtype与eventhandler的映射关系

    private ApplicationContext applicationContext;


    //初始化时调用
    @Override
    public void afterPropertiesSet() throws Exception{
        Map<String,EventHandler> beans=applicationContext.getBeansOfType(EventHandler.class);//获取上下文中所有eventhandler注册的bean
        if(beans!=null){
            for(Map.Entry<String,EventHandler> entry:beans.entrySet()){
                List<EventType> eventTypes=entry.getValue().getSupportEventType();
                for(EventType type:eventTypes){
                    if(!config.containsKey(type)){
                        config.put(type,new ArrayList<EventHandler>());
                    }
                    config.get(type).add(entry.getValue());//建立eventtype与eventhandler映射关系
                }
            }
        }

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
//                该线程负责一直从队列取事件，采用redis的brpop（阻塞形式）
                while(true){
                    String key= RedisKeyUtils.getBizEventKey();
                    List<String> event=jedisClient.brpop(0,key);//redis的实现是先返回key再返回value
                    for(String msg:event){
                        if(msg.contains(key)){
                            continue;
                        }
                        EventModel eventModel= JSON.parseObject(msg,EventModel.class);//将json格式转换成对应的对象
                        if(!config.containsKey(eventModel.getEventType())){
                            logger.error("不存在该类型事件");
                            continue;
                        }
                        for(EventHandler eventHandler:config.get(eventModel.getEventType())){
                            eventHandler.doHandler(eventModel);
                        }
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

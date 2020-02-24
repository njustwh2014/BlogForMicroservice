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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



//本类用于将事件push入队列
@Service
public class EventProducer {
    @Autowired
    JedisClient jedisClient;

    public boolean fireEvent(EventModel eventModel){
        try{
            String json= JSON.toJSONString(eventModel);
            String key= RedisKeyUtils.getBizEventKey();
            jedisClient.lpush(key,json);

        }catch(Exception e){
            return false;
        }
        return true;
    }
}

/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.jedis.impl;

import cn.edu.seu.wh.blog.controller.UserController;
import cn.edu.seu.wh.blog.jedis.JedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

@Component
public class JedisClientPool implements JedisClient, InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private JedisPool jedisPool;


    @Override
    public void afterPropertiesSet() throws Exception {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1000);//设置最大连接池数量
//        jedisPool = new JedisPool("redis://localhost:6379/10");
       jedisPool = new JedisPool(config,"127.0.0.1",6379,2000,"wanghuan");
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis=null;
        String result=null;
        try{
            jedis = jedisPool.getResource();
            result = jedis.set(key, value);
        }catch (Exception e){
            logger.error("redis的set操作发生异常"+e.getMessage());
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return result;
    }

    @Override
    public String get(String key) {
        Jedis jedis=null;
        String result=null;
        try{
            jedis = jedisPool.getResource();
            result = jedis.get(key);
        }catch (Exception e){
            logger.error("redis的get操作发生异常"+e.getMessage());
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return result;
    }

    @Override
    public Boolean exists(String key) {
        Jedis jedis=null;
        Boolean result=null;
        try{
            jedis = jedisPool.getResource();
            result = jedis.exists(key);
        }catch (Exception e){
            logger.error("redis的exists操作发生异常"+e.getMessage());
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return result;
    }

    @Override
    public Long expire(String key, int seconds) {
        Jedis jedis=null;
        Long result=null;
        try{
            jedis = jedisPool.getResource();
            result = jedis.expire(key, seconds);
        }catch (Exception e){
            logger.error("redis的expire操作发生异常"+e.getMessage());
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return result;
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis=null;
        Long result=null;
        try{
            jedis = jedisPool.getResource();
            result = jedis.ttl(key);
        }catch (Exception e){
            logger.error("redis的ttl操作发生异常"+e.getMessage());
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return result;
    }

    @Override
    public Long incr(String key) {
        Jedis jedis=null;
        Long result=null;
        try{
            jedis = jedisPool.getResource();
            result = jedis.incr(key);
        }catch (Exception e){
            logger.error("redis的incr操作发生异常"+e.getMessage());
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return result;
    }

    @Override
    public Long hset(String key, String field, String value) {
        Jedis jedis=null;
        Long result=null;
        try{
            jedis = jedisPool.getResource();
            result = jedis.hset(key, field, value);
        }catch (Exception e){
            logger.error("redis的hset操作发生异常"+e.getMessage());
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return result;
    }

    @Override
    public String hget(String key, String field) {
        Jedis jedis=null;
        String result=null;
        try{
            jedis = jedisPool.getResource();
            result = jedis.hget(key, field);
        }catch (Exception e){
            logger.error("redis的hget操作发生异常"+e.getMessage());
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return result;
    }

    @Override
    public Long hdel(String key, String... field) {
        Jedis jedis=null;
        Long result=null;
        try{
            jedis = jedisPool.getResource();
            result = jedis.hdel(key, field);
        }catch (Exception e){
            logger.error("redis的hdel操作发生异常"+e.getMessage());
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return result;
    }

    @Override
    public Boolean hexists(String key, String field) {
        Jedis jedis=null;
        try{
            jedis = jedisPool.getResource();
            return jedis.hexists(key, field);
        }catch (Exception e){
            logger.error("redis的hexists操作发生异常"+e.getMessage());
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return false;
    }

    @Override
    public List<String> hvals(String key) {
        Jedis jedis=null;
        List<String> result=null;
        try{
            jedis = jedisPool.getResource();
            result = jedis.hvals(key);
        }catch (Exception e){
            logger.error("redis的hvals操作发生异常"+e.getMessage());
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return result;
    }

    @Override
    public long del(String key) {
        Jedis jedis=null;
        Long result=null;
        try{
            jedis = jedisPool.getResource();
            result = jedis.del(key);
        }catch (Exception e){
            logger.error("redis的del操作发生异常"+e.getMessage());
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return result;
    }

    @Override
    public long sadd(String key,String value){
        Jedis jedis =null;
        try{
            jedis = jedisPool.getResource();
            return jedis.sadd(key,value);
        }catch (Exception e ){
            logger.error("发生异常"+e.getMessage());
        }finally {
            jedis.close();
        }
        return 0;
    }

    @Override
    public long srem(String key,String value){
        Jedis jedis =null;
        try{
            jedis = jedisPool.getResource();
            return jedis.srem(key,value);
        }catch (Exception e ){
            logger.error("发生异常"+e.getMessage());
        }finally {
            jedis.close();
        }
        return 0;
    }

    @Override
    public long scard(String key){
        Jedis jedis =null;
        try{
            jedis = jedisPool.getResource();
            return jedis.scard(key);
        }catch (Exception e ){
            logger.error("发生异常"+e.getMessage());
        }finally {
            jedis.close();
        }
        return 0;
    }

    @Override
    public boolean sismember(String key,String value){
        Jedis jedis =null;
        try{
            jedis = jedisPool.getResource();
            return jedis.sismember(key,value);
        }catch (Exception e ){
            logger.error("发生异常"+e.getMessage());
        }finally {
            jedis.close();
        }
        return false;
    }

    @Override
    public long lpush(String key,String value){
        Jedis jedis =null;
        try{
            jedis = jedisPool.getResource();
            return jedis.lpush(key,value);
        }catch (Exception e){
            logger.error("发生异常"+e.getMessage());
        }finally{
            jedis.close();
        }
        return 0;
    }

    @Override
    public List<String> brpop(int timeout,String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return jedis.brpop(timeout,key);
        }catch (Exception e){

        }finally {
            jedis.close();
        }
        return null;
    }

}

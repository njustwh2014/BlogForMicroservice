/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.async.EventHandlerImpl;

import cn.edu.seu.wh.blog.async.EventHandler;
import cn.edu.seu.wh.blog.async.EventModel;
import cn.edu.seu.wh.blog.async.EventType;
import cn.edu.seu.wh.blog.dao.ArticleViewInfoDao;
import cn.edu.seu.wh.blog.dao.ArticleWeightDao;
import cn.edu.seu.wh.blog.jedis.JedisClient;
import cn.edu.seu.wh.blog.model.ArticleViewInfo;
import cn.edu.seu.wh.blog.model.ArticleWeight;
import cn.edu.seu.wh.blog.utils.RedisKeyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import java.util.Arrays;
import java.util.List;


//计算文章热度
@Component
public class CalculateWeight implements EventHandler {
    private static final Logger logger = LoggerFactory.getLogger(CalculateWeight.class);
    @Autowired
    JedisClient jedisClient;
    @Autowired
    ArticleWeightDao articleWeightDao;
    @Autowired
    ArticleViewInfoDao articleViewInfoDao;
    @Override
    public void doHandler(EventModel eventModel) {
        try{
            String viewkey= RedisKeyUtils.getViewKey(eventModel.getEntityid());
            String commentkey=RedisKeyUtils.getCommentKey(eventModel.getEntityid());
            long viewcount=jedisClient.scard(viewkey);
            long commentcount=jedisClient.scard(commentkey);
            long weight=viewcount*2+commentcount*8;
            ArticleViewInfo articleViewInfo=articleViewInfoDao.SelectByArticleID(eventModel.getEntityid());
            articleViewInfo.setViewcount(viewcount);
            articleViewInfo.setCommentcount(commentcount);
            articleViewInfoDao.updateArticleViewInfo(articleViewInfo);
            ArticleWeight articleWeight=articleWeightDao.selectByArticleid(eventModel.getEntityid());
            articleWeight.setWeight(weight);
            articleWeightDao.updateArticleWeight(articleWeight);
        }catch (Exception e){
            logger.error("异步计算文章热度出现错误！:");
            logger.error(String.valueOf(e));
        }
    }

    @Override
    public List<EventType> getSupportEventType() {
        return Arrays.asList(EventType.WEIGHT);
    }
}

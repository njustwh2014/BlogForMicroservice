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
import cn.edu.seu.wh.blog.dao.SystemMessageDao;
import cn.edu.seu.wh.blog.model.SystemMessage;
import cn.edu.seu.wh.blog.model.User;
import cn.edu.seu.wh.blog.service.ArticleService;
import cn.edu.seu.wh.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
* 处理发表评论后系统提醒文章发表者和该文章收藏者用户查看
* */
@Component
public class CommentHandler implements EventHandler {
    private static final Logger logger = LoggerFactory.getLogger(CommentHandler.class);
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    @Autowired
    SystemMessageDao systemMessageDao;

    @Override
    public void doHandler(EventModel eventModel) {
        try{
            //对于文章作者的提醒
            SystemMessage systemMessage=new SystemMessage();
            systemMessage.setEntityauthor(eventModel.getEntityownerid());
            systemMessage.setEntityid(eventModel.getEntityid());//对应文章的id
            systemMessage.setEntitytype(1);
            User sender=userService.getUserInfoByUserIdAndReturnUSer(eventModel.getAuthorid());
            systemMessage.setMessagecontent("用户 "+sender.getNickname()+" 对您的文章发表了新评论，快去看看吧!");
            systemMessage.setListener(eventModel.getEntityownerid());
            systemMessage.setMessagedate(new Date());
            systemMessage.setSender(sender.getId());
            systemMessage.setMessagetype(4);
            systemMessage.setMessagestatus(0);
            if(systemMessage.getListener()!=systemMessage.getSender()){
                systemMessageDao.addSystemmessage(systemMessage);
            }


            //对于文章收藏者的提醒
            List<Map<String,Object>>  collectors=articleService.getCollectorByArticleid(systemMessage.getEntityid());
            systemMessage.setMessagecontent("用户 "+sender.getNickname()+" 对您收藏的文章发表了新评论，快去看看吧!");

            if(collectors!=null){
                for(Map<String,Object> collector:collectors){
                    systemMessage.setListener( Integer.valueOf(collector.get("userid").toString()));
                    if(systemMessage.getListener()!=systemMessage.getSender()){
                        systemMessageDao.addSystemmessage(systemMessage);
                    }
                }
            }


        }catch(Exception e){
            logger.error("异步评论操作出错"+e.getMessage());
        }


    }

    @Override
    public List<EventType> getSupportEventType() {
        return Arrays.asList(EventType.COMMENT);
    }
}

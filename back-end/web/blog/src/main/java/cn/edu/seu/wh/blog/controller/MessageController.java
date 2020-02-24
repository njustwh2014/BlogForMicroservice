/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.controller;

import cn.edu.seu.wh.blog.model.SystemMessage;
import cn.edu.seu.wh.blog.result.ResultInfo;
import cn.edu.seu.wh.blog.service.MessageService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/message") //对于前端请求处理
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    @Autowired
    MessageService messageService;
    /*
    * 接口：获取用户未读消息数
    *
    * */
    @RequestMapping(value = "/unreadstatus/{userid}",method = RequestMethod.GET)
    public ResultInfo getUserUnreadMessageStatus(@PathVariable Integer userid){
        try{
            Map<String,Object> message=new HashMap<>();
            Integer total=messageService.validateUserHaveUnreadMessage(userid);
            message.put("total",total);
            if(total==0){
                return ResultInfo.ok(message);
            }
            List<SystemMessage> systemMessageList=messageService.getFourUnreadMessage(userid);
            message.put("messagelist",systemMessageList);
            return ResultInfo.ok(message);

        }catch (Exception e){
            logger.error("获取用户未读消息数目时出现异常！"+e.getMessage());
            e.printStackTrace();
            return ResultInfo.build(500,"获取用户未读消息数目时出现异常！");
        }
    }

    /*
    * 接口：获取用户所有未读消息
    * */
    @RequestMapping(value="/unreadmessage/{userid}",method = RequestMethod.GET)
    public ResultInfo getAllUnreadMessage(@PathVariable Integer userid){
        try{
            List<SystemMessage> systemMessageList=messageService.getAllUnreadMessage(userid);
            Integer total=systemMessageList.size();
            Map<String,Object> ret=new HashMap<>();
            ret.put("total",total);
            ret.put("systemmessagelist",systemMessageList);
            return ResultInfo.ok(ret);
        }catch (Exception e){
            logger.error("获取用户未读消息时出现异常！"+e.getMessage());
            e.printStackTrace();
            return ResultInfo.build(500,"获取用户未读消息时出现异常！");
        }
    }
    /*
    * 接口：根据batch获取用户历史消息
    * */
    @RequestMapping(value="/readmessage",method = RequestMethod.POST)
    public ResultInfo getReadMessageByBatch(@RequestBody JSONObject jsonObject){
        Integer userid=jsonObject.getInteger("userid");
        Integer index=jsonObject.getInteger("index");
        Integer batchSize=jsonObject.getInteger("batchsize");
        try{
            return ResultInfo.ok(messageService.getAReadMessageByBatch(userid,index,batchSize));
        }catch(Exception e){
            logger.error("获取用户历史消息时出现异常！"+e.getMessage());
            e.printStackTrace();
            return ResultInfo.build(500,"获取用户历史消息时出现异常！");
        }
    }

    /*
    * 接口：将未读消息设置成已读
    * */
    @RequestMapping(value = "/changemessage/{messageid}",method = RequestMethod.GET)
    public ResultInfo changeMessageStatus(@PathVariable Integer messageid){
        try{
            return ResultInfo.ok(messageService.changeMessageStatus(messageid));
        }catch(Exception e){
            logger.error("修改消息异常时出现异常！"+e.getMessage());
            e.printStackTrace();
            return ResultInfo.build(500,"修改消息异常时出现异常！");
        }
    }
}

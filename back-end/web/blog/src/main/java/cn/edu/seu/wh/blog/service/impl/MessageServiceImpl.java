/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.service.impl;

import cn.edu.seu.wh.blog.dao.SystemMessageDao;
import cn.edu.seu.wh.blog.model.SystemMessage;
import cn.edu.seu.wh.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {


    @Autowired
    SystemMessageDao systemMessageDao;
    /*
    * 验证用户是否有未读消息
    * @输入：用户id
    * @输出：未读消息数
    * */
    @Override
    public Integer validateUserHaveUnreadMessage(Integer userid) {
        Integer ret=systemMessageDao.getUserUnreadStatus(userid);
        return ret;
    }

    /*
     * 获取前4条未读消息
     * @输入：用户id
     * @输出：未读消息
     * */

    @Override
    public List<SystemMessage> getFourUnreadMessage(Integer userid) {
        List<SystemMessage> ret=systemMessageDao.getFourUnreadMessage(userid);
        return ret;
    }


    /*
     * 获取用户所有未读消息
     * @输入：用户id
     * @输出：未读消息
     * */
    @Override
    public List<SystemMessage> getAllUnreadMessage(Integer userid) {
        return systemMessageDao.getAllUnreadMessage(userid);
    }

    /*
     * 获取用户历史消息
     * @输入： 用户id index batchSize
     * @输出：历史消息
     * */
    @Override
    public List<SystemMessage> getAReadMessageByBatch(Integer userid, Integer index, Integer batchSize) {
        return systemMessageDao.getReadMessageByBatch(userid,index*batchSize,batchSize);
    }

    /*
     * 将未读消息设置成已读
     * @输入：消息id
     * @输出：修改记录数
     * */
    @Override
    public Integer changeMessageStatus(Integer messageId) {
        return systemMessageDao.changeMessageStatus(messageId);
    }
}

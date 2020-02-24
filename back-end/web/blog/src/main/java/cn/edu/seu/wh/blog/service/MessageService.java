/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.service;


import cn.edu.seu.wh.blog.model.SystemMessage;

import java.util.List;

public interface MessageService {

    /*
     * 验证用户是否有未读消息
     * @输入：用户id
     * @输出：未读消息数
     * */
    public Integer validateUserHaveUnreadMessage(Integer userid);

    /*
    * 获取前4条未读消息
    * @输入：用户id
    * @输出：未读消息
    * */
    public List<SystemMessage> getFourUnreadMessage(Integer userid);

    /*
    * 获取用户所有未读消息
    * @输入：用户id
    * @输出：未读消息
    * */
    public List<SystemMessage> getAllUnreadMessage(Integer userid);

    /*
    * 获取用户历史消息
    * @输入： 用户id index batchSize
    * @输出：历史消息
    * */
    public List<SystemMessage> getAReadMessageByBatch(Integer userid,Integer index,Integer batchSize);

    /*
    * 将未读消息设置成已读
    * @输入：消息id
    * @输出：修改记录数
    * */
    public Integer changeMessageStatus(Integer messageId);

}

/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.async;

import java.util.HashMap;
import java.util.Map;

public class EventModel {
    private EventType eventType;//事件的类型，用于判断这是何种事件
    private Integer authorid;//事件发起者
    private Integer entityid;//实体id
    private Integer entityownerid;//实体拥有者id
    private Map<String,String> exts = new HashMap<>();//定义扩展的字段，方便今后向事件的实体中添加新的字段

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Map<String, String> getExts() {
        return exts;
    }

    public void setExts(Map<String, String> exts) {
        this.exts = exts;
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public Integer getEntityid() {
        return entityid;
    }

    public void setEntityid(Integer entityid) {
        this.entityid = entityid;
    }

    public Integer getEntityownerid() {
        return entityownerid;
    }

    public void setEntityownerid(Integer entityownerid) {
        this.entityownerid = entityownerid;
    }
}

/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.async;


//此枚举类用于定义事件类型
public enum EventType {

    WEIGHT(0),//计算文章热度
    DELETEARTICLE(1),//删除文章
    DELETECOMMENT(2),//删除评论
    COMMENT(3),//发表评论
    ;
    private int value;
    EventType(int value){
        this.value=value;
    }
    public int getValue(){
        return value;
    }
}

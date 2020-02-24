/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.result;



public class ResultInfo {
    private Integer status;//响应业务状态
    private String msg;//响应消息
    private Object data;//响应中的数据

    public  ResultInfo(Integer status,String msg,Object data){
        this.status=status;
        this.msg=msg;
        this.data=data;
    }

    public ResultInfo(Object data){
        this.status=200;
        this.msg="ok";
        this.data=data;
    }

    public ResultInfo(){

    }

    public static ResultInfo build(Integer status,String msg, Object data){
        return new ResultInfo(status, msg, data);
    }

    public static  ResultInfo build(Integer status,String msg){
        return new ResultInfo(status,msg,null);
    }

    public static ResultInfo ok(Object data){
        return new ResultInfo(data);
    }

    public  static  ResultInfo ok(){
        return new ResultInfo(null);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

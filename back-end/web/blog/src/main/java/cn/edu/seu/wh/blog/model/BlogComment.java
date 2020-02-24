/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.model;

import java.util.Date;
import java.util.List;

public class BlogComment {
    private List<BlogComment> BlogCommentReplyList=null;
    private Integer id;
    private  Integer userId;
    private String nickName;
    private String headUrl;
    private String content;
    private Date createTime;
    private Integer likeCount;
    private Integer dislikeCount;
    private Integer entityId;
    private Integer entityType;
    private Integer replyCount;
    private Integer status;
    private Integer toCommentId;
    private Integer toUserId;
    private String toNickName;

    public List<BlogComment> getBlogCommentReplyList() {
        return BlogCommentReplyList;
    }

    public void setBlogCommentReplyList(List<BlogComment> blogCommentReplyList) {
        BlogCommentReplyList = blogCommentReplyList;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public String getToNickName() {
        return toNickName;
    }

    public void setToNickName(String toNickName) {
        this.toNickName = toNickName;
    }

    public Integer getToCommentId() {
        return toCommentId;
    }

    public void setToCommentId(Integer toCommentId) {
        this.toCommentId = toCommentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(Integer dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public Integer getEntityType() {
        return entityType;
    }

    public void setEntityType(Integer entityType) {
        this.entityType = entityType;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "BlogComment{" +
                "BlogCommentReplyList=" + BlogCommentReplyList +
                ", id=" + id +
                ", userId=" + userId +
                ", nickName='" + nickName + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", likeCount=" + likeCount +
                ", dislikeCount=" + dislikeCount +
                ", entityId=" + entityId +
                ", entityType=" + entityType +
                ", replyCount=" + replyCount +
                ", status=" + status +
                ", toCommentId=" + toCommentId +
                ", toUserId=" + toUserId +
                ", toNickName='" + toNickName + '\'' +
                '}';
    }
}

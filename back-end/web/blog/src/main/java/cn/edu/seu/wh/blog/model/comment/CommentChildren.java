/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.model.comment;


import cn.edu.seu.wh.blog.model.User;

import java.util.Date;
import java.util.List;

public class CommentChildren {
    private Integer id;
    private Integer articleid;
    private User author;
    private User toUser;
    private Date createdate;
    private String content;
    private Integer atlevel;
    private long likecount;
    private Comment parentcomment;
    private Comment toComment;
    private boolean islike;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAtlevel() {
        return atlevel;
    }

    public void setAtlevel(Integer atlevel) {
        this.atlevel = atlevel;
    }

    public long getLikecount() {
        return likecount;
    }

    public void setLikecount(long likecount) {
        this.likecount = likecount;
    }

    public Comment getParentcomment() {
        return parentcomment;
    }

    public void setParentcomment(Comment parentcomment) {
        this.parentcomment = parentcomment;
    }

    public Comment getToComment() {
        return toComment;
    }

    public void setToComment(Comment toComment) {
        this.toComment = toComment;
    }

    public boolean isIslike() {
        return islike;
    }

    public void setIslike(boolean islike) {
        this.islike = islike;
    }
}

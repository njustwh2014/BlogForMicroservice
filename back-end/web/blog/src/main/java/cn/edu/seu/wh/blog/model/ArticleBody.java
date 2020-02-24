/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.model;

public class ArticleBody {
    //对应的表aticlebody
    private Integer id;
    private Integer articleid;

    private String content;
    private String contenthtml;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public String getContenthtml() {
        return contenthtml;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public void setContenthtml(String contenthtml) {
        this.contenthtml = contenthtml;
    }

    @Override
    public String toString() {
        return "ArticleBody{" +
                "id=" + id +
                ", articleid=" + articleid +
                ", content='" + content + '\'' +
                ", contenthtml='" + contenthtml + '\'' +
                '}';
    }
}

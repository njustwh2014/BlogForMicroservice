/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.model;

public class TagId {
    //用于json接收
    private Integer id;
    private String tagdescription;

    public String getTagdescription() {
        return tagdescription;
    }

    public void setTagdescription(String tagdescription) {
        this.tagdescription = tagdescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TagId{" +
                "id=" + id +
                ", tagdescription='" + tagdescription + '\'' +
                '}';
    }
}

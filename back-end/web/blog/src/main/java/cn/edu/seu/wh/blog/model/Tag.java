/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.model;

public class Tag {
    private Integer id;
    private String tagdescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagdescription() {
        return tagdescription;
    }

    public void setTagdescription(String tagdescription) {
        this.tagdescription = tagdescription;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tagdescription='" + tagdescription + '\'' +
                '}';
    }
}

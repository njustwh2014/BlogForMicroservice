/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.seu.wh.blog.model;
import java.util.Date;

public class User {
    private Integer id;
    private String account;
    private String password;
    private Integer registerstatus;
    private Integer IPaddress;
    private Date lasttime;
    private Integer authority;
    private String nickname;
    private Integer gender;
    private Date birthdate;
    private String school;
    private String text;
    private String headportraiturl;
    private Integer contributiondegree;
    private String token;
    private String salt;
    private String hobby;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRegisterstatus() {
        return registerstatus;
    }

    public void setRegisterstatus(Integer registerstatus) {
        this.registerstatus = registerstatus;
    }

    public Integer getIPaddress() {
        return IPaddress;
    }

    public void setIPaddress(Integer IPaddress) {
        this.IPaddress = IPaddress;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHeadportraiturl() {
        return headportraiturl;
    }

    public void setHeadportraiturl(String headportraiturl) {
        this.headportraiturl = headportraiturl;
    }

    public Integer getContributiondegree() {
        return contributiondegree;
    }

    public void setContributiondegree(Integer contribution_degree) {
        this.contributiondegree = contribution_degree;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", registerstatus=" + registerstatus +
                ", IPaddress=" + IPaddress +
                ", lastime=" + lasttime +
                ", authority=" + authority +
                ", nickname='" + nickname + '\'' +
                ", gender=" + gender +
                ", birthdate=" + birthdate +
                ", school='" + school + '\'' +
                ", text='" + text + '\'' +
                ", headportraiturl='" + headportraiturl + '\'' +
                ", contributiondegree=" + contributiondegree +
                ", token='" + token + '\'' +
                ", salt='" + salt + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}

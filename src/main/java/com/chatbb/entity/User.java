package com.chatbb.entity;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2022-03-25 23:19:13
 */
public class User implements Serializable {
    private static final long serialVersionUID = 848590386326005790L;
    /**
    * 用户id
    */
    private Integer uid;
    /**
    * 用户密码
    */
    private String upassword;
    /**
    * 用户邮箱
    */
    private String uemail;
    /**
    * 邮箱验证码
    */
    private String uemailcode;
    /**
    * 性别
    */
    private String ugender;
    /**
    * 个性签名
    */
    private String upersonalizedsignature;
    /**
    * 当前状态，在线，离线等
    */
    private String ustate;

    private String unickname;

    private String token;

    private String lastMessage;

    private String lastMessageDate;

    private Integer unreadnum;

    private String avatar;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUemailcode() {
        return uemailcode;
    }

    public void setUemailcode(String uemailcode) {
        this.uemailcode = uemailcode;
    }

    public String getUgender() {
        return ugender;
    }

    public void setUgender(String ugender) {
        this.ugender = ugender;
    }

    public String getUpersonalizedsignature() {
        return upersonalizedsignature;
    }

    public void setUpersonalizedsignature(String upersonalizedsignature) {
        this.upersonalizedsignature = upersonalizedsignature;
    }

    public String getUstate() {
        return ustate;
    }

    public void setUstate(String ustate) {
        this.ustate = ustate;
    }

    public String getUnickname() {
        return unickname;
    }

    public void setUnickname(String unickname) {
        this.unickname = unickname;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastMessageDate() {
        return lastMessageDate;
    }

    public void setLastMessageDate(String lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
    }


    public Integer getUnreadnum() {
        return unreadnum;
    }

    public void setUnreadnum(Integer unreadnum) {
        this.unreadnum = unreadnum;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", upassword='" + upassword + '\'' +
                ", uemail='" + uemail + '\'' +
                ", uemailcode='" + uemailcode + '\'' +
                ", ugender='" + ugender + '\'' +
                ", upersonalizedsignature='" + upersonalizedsignature + '\'' +
                ", ustate='" + ustate + '\'' +
                ", unickname='" + unickname + '\'' +
                ", token='" + token + '\'' +
                ", lastMessage='" + lastMessage + '\'' +
                ", lastMessageDate='" + lastMessageDate + '\'' +
                ", unreadnum=" + unreadnum +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
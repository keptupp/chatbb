package com.chatbb.entity;

import java.io.Serializable;

/**
 * (Friendrequest)实体类
 *
 * @author makejava
 * @since 2022-03-31 13:13:15
 */
public class Friendrequest implements Serializable {
    private static final long serialVersionUID = 104897000245409038L;
    /**
    * 用户的id
    */
    private Integer uid;
    /**
    * 请求的id
    */
    private Integer requestid;
    /**
    * 请求的时间
    */
    private String requestdate;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRequestid() {
        return requestid;
    }

    public void setRequestid(Integer requestid) {
        this.requestid = requestid;
    }

    public String getRequestdate() {
        return requestdate;
    }

    public void setRequestdate(String requestdate) {
        this.requestdate = requestdate;
    }

}
package com.chatbb.entity;

import java.io.Serializable;

/**
 * (Likerecord)实体类
 *
 * @author makejava
 * @since 2022-04-20 10:56:33
 */
public class Likerecord implements Serializable {
    private static final long serialVersionUID = 368835862364238961L;
    
    private Integer lrid;
    
    private Integer said;
    
    private Integer userid;

    private String unickname;
    
    private Boolean isrecord;
    
    private String record;
    
    private Boolean islike;


    public Integer getLrid() {
        return lrid;
    }

    public void setLrid(Integer lrid) {
        this.lrid = lrid;
    }

    public Integer getSaid() {
        return said;
    }

    public void setSaid(Integer said) {
        this.said = said;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUnickname() {
        return unickname;
    }

    public void setUnickname(String unickname) {
        this.unickname = unickname;
    }

    public Boolean getIsrecord() {
        return isrecord;
    }

    public void setIsrecord(Boolean isrecord) {
        this.isrecord = isrecord;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public Boolean getIslike() {
        return islike;
    }

    public void setIslike(Boolean islike) {
        this.islike = islike;
    }


    @Override
    public String toString() {
        return "Likerecord{" +
                "lrid=" + lrid +
                ", said=" + said +
                ", userid=" + userid +
                ", unickname='" + unickname + '\'' +
                ", isrecord=" + isrecord +
                ", record='" + record + '\'' +
                ", islike=" + islike +
                '}';
    }
}
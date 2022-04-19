package com.chatbb.entity;

import java.io.Serializable;

/**
 * (Friendstable)实体类
 *
 * @author makejava
 * @since 2022-03-31 23:11:21
 */
public class Friendstable implements Serializable {
    private static final long serialVersionUID = 279277554954244171L;
    
    private Integer userid;
    
    private Integer friendid;
    /**
    * 用于记录user是否添加对方为好友
    */
    private String adddate;
    /**
    * 记录与好友发送但未读的数量
    */
    private String relationship;
    /**
    * 好友的备注
    */
    private String remark;


    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getFriendid() {
        return friendid;
    }

    public void setFriendid(Integer friendid) {
        this.friendid = friendid;
    }

    public String getAdddate() {
        return adddate;
    }

    public void setAdddate(String adddate) {
        this.adddate = adddate;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
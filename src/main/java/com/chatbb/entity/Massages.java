package com.chatbb.entity;

import java.io.Serializable;

/**
 * (Massages)实体类
 *
 * @author makejava
 * @since 2022-04-02 21:33:03
 */
public class Massages implements Serializable {
    private static final long serialVersionUID = 320024758146029347L;
    
    private Integer mid;
    
    private Integer userid;
    
    private Integer friendid;
    /**
    * 发送的消息
    */
    private String massage;
    
    private String massagedate;


    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

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

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public Object getMassagedate() {
        return massagedate;
    }

    public void setMassagedate(String massagedate) {
        this.massagedate = massagedate;
    }

    @Override
    public String toString() {
        return "Massages{" +
                "mid=" + mid +
                ", userid=" + userid +
                ", friendid=" + friendid +
                ", massage='" + massage + '\'' +
                ", massagedate='" + massagedate + '\'' +
                '}';
    }
}
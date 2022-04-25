package com.chatbb.entity;

import java.io.Serializable;
import java.util.List;

/**
 * (Spaceactivaity)实体类
 *
 * @author makejava
 * @since 2022-04-19 16:09:15
 */
public class Spaceactivaity implements Serializable {
    private static final long serialVersionUID = 124368816989323837L;
    /**
    * 空间id
    */
    private Integer said;
    /**
    * 用户id
    */
    private Integer userid;

    /**
     * 用户的昵称，通过sql补齐
     */
    private String unickname;

    /**
     * 用户的头像，通过sql补齐
     */
    private String avatar;
    /**
    * 动态文字
    */
    private String astext;
    /**
    * 第一张图片
    */
    private String image1;
    /**
    * 第二张图片
    */
    private String image2;
    /**
    * 发布时间，后续这个表会加一个点赞数
    */
    private String sadate;


    /**
     * 点赞数,数据库没有的字段
     */
    private Integer like;

    /**
     * 数据库没有的字段
     */
    private List<Likerecord> likeRecords;


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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAstext() {
        return astext;
    }

    public void setAstext(String astext) {
        this.astext = astext;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getSadate() {
        return sadate;
    }

    public void setSadate(String sadate) {
        this.sadate = sadate;
    }


    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public List<Likerecord> getLikeRecords() {
        return likeRecords;
    }

    public void setLikeRecords(List<Likerecord> likeRecords) {
        this.likeRecords = likeRecords;
    }

    @Override
    public String toString() {
        return "Spaceactivaity{" +
                "said=" + said +
                ", userid=" + userid +
                ", unickname='" + unickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", astext='" + astext + '\'' +
                ", image1='" + image1 + '\'' +
                ", image2='" + image2 + '\'' +
                ", sadate='" + sadate + '\'' +
                ", like=" + like +
                ", likeRecords=" + likeRecords +
                '}';
    }
}
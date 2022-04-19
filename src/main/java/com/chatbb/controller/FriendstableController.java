package com.chatbb.controller;

import com.chatbb.service.FriendstableService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Friendstable)表控制层
 *
 * @author makejava
 * @since 2022-03-31 23:11:27
 */
@RestController
@RequestMapping("friendstable")
public class FriendstableController {
    /**
     * 服务对象
     */
    @Resource
    private FriendstableService friendstableService;



    /**
     * 同意添加该好友，去好友表中添加两人的关系,并删除请求表的两个请求
     * @param myid
     * @param friendid
     * @return
     */
    @GetMapping("agreeAdd")
    public String agreeAdd(String myid,String friendid){
        friendstableService.insert(myid,friendid);
        return "已同意";
    }

    @GetMapping("updateUnReadNum")
    public String updateUnReadNum(Integer userid,Integer friendid,Integer unreadnum){
        this.friendstableService.updateUnReadNum(userid,friendid,unreadnum==0?0:unreadnum-1);
        return "未读数更新成功";
    }

    @GetMapping("deleteFriend")
    public String deleteFriend(Integer userid,Integer friendid){
        System.out.println(userid+"    "+friendid);
        this.friendstableService.deleteFriend(userid,friendid);
        return "删除成功";
    }

}
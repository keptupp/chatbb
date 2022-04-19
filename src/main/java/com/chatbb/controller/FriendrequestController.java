package com.chatbb.controller;

import com.chatbb.entity.Friendrequest;
import com.chatbb.service.FriendrequestService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * (Friendrequest)表控制层
 *
 * @author makejava
 * @since 2022-03-31 13:13:15
 */
@RestController
@RequestMapping("friendrequest")
public class FriendrequestController {
    /**
     * 服务对象
     */
    @Resource
    private FriendrequestService friendrequestService;


    /*
    这个对象当时有主键，后来发现不能有，就删了主键
     */
    @GetMapping("addOneRequest")
    public String addOneRequest(@Param("uid") String uid,@Param("requestid") String requestid){
        Friendrequest friendrequest=new Friendrequest();
        friendrequest.setUid(Integer.valueOf(uid));
        friendrequest.setRequestid(Integer.valueOf(requestid));
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        friendrequest.setRequestdate(dateFormat.format(date));

        return friendrequestService.insert(friendrequest);
    }

    @GetMapping("refuseAdd")
    public String refuseAdd(Integer uid,Integer requestid){
        this.friendrequestService.refuseAdd(uid,requestid);
        return "已拒绝";
    }


}
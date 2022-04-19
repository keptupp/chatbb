package com.chatbb.controller;

import com.chatbb.entity.Friendrequest;
import com.chatbb.entity.Friendstable;
import com.chatbb.entity.User;
import com.chatbb.service.FriendrequestService;
import com.chatbb.service.FriendstableService;
import com.chatbb.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2022-03-25 23:19:17
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @Resource
    private FriendrequestService friendrequestService;

    @Resource
    private FriendstableService friendstableService;
    /**`
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(Integer id) {
        return this.userService.queryById(id);
    }



    /**
     * 根据id或者name查好友
     * @param uidoruname
     * @return
     */
    @RequestMapping("searchUserByidname")
    public List<User> searchUserByidname(String uidoruname){
        return userService.searchUserByidname(uidoruname);
    }

    /**
     * 根据uid去好友表里面查询好友id，然后更具id到用户表里面查询用户并返回
     * @param uid
     * @return
     */
    @GetMapping("queryFriendList")
    public List<User> queryFriendList(Integer uid){
        return userService.queryFriendList(uid);
    }


//    根据传回的uid，去friendrequest中查询请求加好友的id，然后根据这些id返回user集合
    /**
     * 查询本ip被请求加好友的人数，先查出有哪些id，再根据id去查出用户具体信息
     * 讲道理，这个应该放在usercontroller里面
     * @param uid
     * @return
     */
    @GetMapping("querybyuid")
    public List<User> queryByUid(String uid){
        List<Friendrequest> friendrequestList = friendrequestService.queryById(Integer.valueOf(uid));

        //这个地方有问题，如果查出来的list为0
        //那么批量查user的list就会把用户全查出来
        //先将就一下
        if(friendrequestList.size()==0){
            return null;
        }

        List<User> users=userService.queryByUidList(friendrequestList);
        return users;
    }


    @GetMapping("updateSignature")
    public String updateSignature(Integer uid,String uPersonalizedsignature){
        this.userService.updateSignature(uid,uPersonalizedsignature);
        return "个性签名修改成功";
    }


}
package com.chatbb.controller;

import com.chatbb.entity.User;
import com.chatbb.service.SendEmail;
import com.chatbb.service.UserService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("login")
public class Login {

    @Autowired
    SendEmail sendEmail;

    @Resource
    private UserService userService;


    /**
     * 登录验证，service层顺便跟新一下token
     * @param user
     * @return
     */
    @RequestMapping("submit")
    public String loginUrl(User user){
        return userService.queryByUidAndUpw(user);
    }

    /**
     * 请求发送验证码的时候就把用户注册，之后提交的时候再去绑定邮箱
     * @param user
     * @return
     */
    @RequestMapping("email")
    public String emaliTest(User user){
        //通过查数据库中是否有改昵称和邮箱判断是否注册
        if(userService.queryByUnickname(user.getUnickname())){
            return "此昵称已使用";
        }else if(userService.queryByEmail(user.getUemail())){
            return "此邮箱已经注册";
        }else{
            //没有用户名，创建新用户,注册信息为昵称，密码，暂存邮箱，与邮箱验证码
            System.out.println("创建新用户");
            String emailCode=sendEmail.send(user.getUemail());
            user.setUemailcode(emailCode);
            User newUser=userService.insert(user);
            return newUser.getUid().toString();
        }
    }

    /**
     * 验证邮箱验证码是否正确
     * @param user
     * @return
     */
    @RequestMapping("checkemailcode")
    public String checkEmailCode(User user){
        User newUser=userService.queryById(user.getUid());
        if(newUser.getUemailcode().equals(user.getUemailcode())){
            //返回的值与生成的相等,正确
            return "验证成功";
        }else{
            //邮箱验证码错误，删除该注册信息
            userService.deleteById(user.getUid());
            return "验证失败";
        }
    }
    @RequestMapping("checkToken")
    Map<String,String> checkToken(User user){
        User user1=userService.checkToken(user.getToken());
        System.out.println(user1.toString());
        Map<String,String> map=new HashMap<>();

        if(user1==null){
            map.put("massage","没有token");
        }else{
            map.put("uid",user1.getUid().toString());
            map.put("unickname",user1.getUnickname());
            map.put("massage","Token验证通过");
            map.put("upersonalizedsignature",user1.getUpersonalizedsignature());
            map.put("avatar",user1.getAvatar());
        }
        return map;
    }

    @RequestMapping("forgetpassword")
    String forgetPassword(String uemail){
        //要么返回uid，要么
        return userService.queryByEmailObject(uemail);
    }

    @RequestMapping("changepassword")
    String changePassword(User user){
        Integer i=userService.changepassword(user);
        if(i.equals(1)){
            return "密码更改成功";
        }else{
            return "密码更改失败，检查验证码是否正确";
        }
    }
}

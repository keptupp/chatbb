package com.chatbb.controller;


import com.chatbb.Utils.MD5;
import com.chatbb.entity.Spaceactivaity;
import com.chatbb.service.SpaceactivaityService;
import com.chatbb.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("file")
public class FileUploadController {

    @Resource
    UserService userService;
    @Resource
    SpaceactivaityService spaceactivaityService;

    @PostMapping("avatarImage")
    public String avatarImage(MultipartFile avatarImage,Integer uid){
        String fileName = uid.toString()+"_avatar.jpg";
        File dest = new File(new File("D:\\IDEAWork\\Vue\\static\\avatar").getAbsolutePath()+ "/" + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            avatarImage.transferTo(dest); // 保存文件
            userService.updateAvatarById(uid,"static/Avatar/"+fileName);//向数据库中也保存文件访问路径
            return "头像上传成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "头像上传失败";
        }
    }

    @PostMapping("spaceImage")
    public String spaceImage(MultipartFile image1,MultipartFile image2,String text,Integer uid){
        String image1Name = MD5.getMD5(uid+image1.getOriginalFilename()+new Date())+".jpg";
        String image2Name = MD5.getMD5(uid+image2.getOriginalFilename()+new Date())+".jpg";
        String date=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        Spaceactivaity spaceactivaity=new Spaceactivaity();
        spaceactivaity.setImage1("static/SpaceImage/"+image1Name);
        spaceactivaity.setImage2("static/SpaceImage/"+image2Name);
        spaceactivaity.setAstext(text);
        spaceactivaity.setSadate(date);
        spaceactivaity.setUserid(uid);
        System.out.println(spaceactivaity.toString());
        //存入数据库
        this.spaceactivaityService.insert(spaceactivaity);
        //存入本地固态
        File file1 = new File(new File("D:\\IDEAWork\\Vue\\static\\SpaceImage").getAbsolutePath()+ "/" + image1Name);
        File file2 = new File(new File("D:\\IDEAWork\\Vue\\static\\SpaceImage").getAbsolutePath()+ "/" + image2Name);
        if (!file1.getParentFile().exists()) {
            file1.getParentFile().mkdirs();
        }
        if (!file2.getParentFile().exists()) {
            file2.getParentFile().mkdirs();
        }
        try {
            image1.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            image2.transferTo(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "动态发布成功";
    }
}

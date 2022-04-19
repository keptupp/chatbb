package com.chatbb.controller;


import com.chatbb.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

@RestController
@RequestMapping("file")
public class FileUploadController {

    @Resource
    UserService userService;

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
    public String spaceImage()
}

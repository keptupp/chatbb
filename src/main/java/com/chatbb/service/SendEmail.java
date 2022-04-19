package com.chatbb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class SendEmail {

    @Autowired
    private JavaMailSenderImpl mailSender;
    @Value("${spring.mail.username}")
    private String from;

    SimpleMailMessage message =new SimpleMailMessage();

    public String send(String qq){
        String emailCode = String.valueOf((int)((Math.random()*9+1)*1000));
        message.setTo(qq);
        message.setReplyTo(qq);
        message.setSubject("用户注册");
        message.setText("欢迎使用BB，您的验证码为："+emailCode+"，用于邮箱绑定BB帐号或找回密码，1分钟内有效，验证码提供给他人可能导致帐号被盗，请勿泄露，谨防被骗。");
        message.setFrom(from);
        mailSender.send(message);
        return emailCode;
    }
}

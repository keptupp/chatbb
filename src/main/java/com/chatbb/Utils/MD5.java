package com.chatbb.Utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MD5 {
    public static String getMD5(String id){


        Date date = new Date();
        byte[] digest = null;

        //将用户id与当前时间结合生成md5码，保证token的唯一
        id=id+date.getTime();
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest  = md5.digest(id.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //16是表示转换为16进制数
        String md5Str = new BigInteger(1, digest).toString(16);
        return md5Str;
    }
}

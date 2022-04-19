package com.chatbb.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chatbb.entity.Massages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WebSocketMassage {


    //将前端得到的数据抽出Massages对象
    public static Massages getMassage(JSONObject jsonObject){
        Massages massages=new Massages();
        massages.setUserid(jsonObject.getInteger("userid"));
        massages.setFriendid(jsonObject.getInteger("friendid"));
        massages.setMassage(jsonObject.getString("massage"));
        massages.setMassagedate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        return massages;
    }

    //放入数据与服务
    public static String getMapData(String service,Massages massages){
        Map<String,String> map=new HashMap<>();
        map.put("service",service);
        map.put("data", JSON.toJSONString(massages));
        return JSON.toJSONString(map);
    }
}

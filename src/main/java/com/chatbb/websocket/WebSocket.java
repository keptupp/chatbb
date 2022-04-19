package com.chatbb.websocket;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chatbb.Utils.WebSocketMassage;
import com.chatbb.entity.Massages;
import com.chatbb.entity.test;
import com.chatbb.service.FriendrequestService;
import com.chatbb.service.FriendstableService;
import com.chatbb.service.MassagesService;
import com.chatbb.service.UserService;
import com.chatbb.service.impl.MassagesServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/websocket/{id}")
public class WebSocket {
    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();
    private static Map<String, Session> sessionPool = new HashMap<String, Session>();

    //spring通常是单列，要这样才能注入
    private static MassagesService massagesService;
    @Resource
    public void setMassagesService(MassagesService massagesService){
        WebSocket.massagesService=massagesService;
    }

    private static FriendstableService friendstableService;
    @Resource
    public void setFriendstableService(FriendstableService friendstableService){
        WebSocket.friendstableService=friendstableService;
    }

    private static UserService userService;
    @Resource
    public void setUserService(UserService userService){
        WebSocket.userService=userService;
    }


    @OnOpen
    public void onOpen(Session session, @PathParam(value = "id") String id) {
        System.out.println(id);
        this.session = session;
        webSockets.add(this);
        sessionPool.put(id, session);
        System.out.println(id + "【websocket消息】有新的连接，总数为:" + webSockets.size());
        //向改用户的在线好友推送他的状态
        //返回他在线好友的id
        List<Integer> listId =friendstableService.queryFidByuid(id);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("service","状态");
        jsonObject.put("ustate","在线");
        jsonObject.put("friendid",id);
        for(int i=0;i<listId.size();i++){
            this.sendOneMessage(listId.get(i).toString(),jsonObject.toJSONString());
        }
        //顺便更改数据库中的状态
        userService.updataStateById(Integer.valueOf(id),"在线");
    }

    @OnClose
    public void onClose(@PathParam(value = "id") String id) {
        webSockets.remove(this);
        System.out.println("【websocket消息】连接断开，总数为:" + webSockets.size()+"断开的id是"+id);
        List<Integer> listId =friendstableService.queryFidByuid(id);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("service","状态");
        jsonObject.put("ustate","离线");
        jsonObject.put("friendid",id);
        for(int i=0;i<listId.size();i++){
            this.sendOneMessage(listId.get(i).toString(),jsonObject.toJSONString());
        }
        userService.updataStateById(Integer.valueOf(id),"离线");
    }

    @OnMessage
    public void onMessage(String massage) {
        System.out.println("【websocket消息】收到客户端消息:" + massage);
        JSONObject jsonObject=JSONObject.parseObject(massage);
        //接受到消息后，通过service字段区分服务
        if("聊天".equals(jsonObject.getString("service"))){
            //为聊天服务，就把聊天记录存进数据库
            Massages massages= WebSocketMassage.getMassage(jsonObject);
            massagesService.insert(massages);
            //然后把消息发送给接收方的websocket,消息不能直接发原始的，要发massages对象，因为历史记录的数组是对象
            String data=WebSocketMassage.getMapData("聊天",massages);
            System.out.println(jsonObject.getString("friendid"));
            this.sendOneMessage(jsonObject.getString("friendid"), data);
        }else if("提醒更新".equals(jsonObject.getString("service"))){
            //为提醒申请好友更新好友列表的服务
            this.sendOneMessage(jsonObject.getString("uid"),massage);
            System.out.println("确实向好友发送提醒更新了");
        }
    }

    public void sendAllMessage(String message) {
        for (WebSocket webSocket : webSockets) {
            System.out.println("【websocket消息】广播消息:" + message);
            try {
                webSocket.session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendOneMessage(String id, String message) {
        System.out.println("【websocket消息】单点消息:" + message);
        Session oneSession=null;
        try {
            oneSession = sessionPool.get(id);
        }catch (NullPointerException e){
            //get不到就会空指针异常
            e.printStackTrace();
        }

        if (oneSession != null) {
            try {
                oneSession.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{

        }
    }

    public static void sendMassageById(String id,String message){
        System.out.println("websocket代理消息,加好友,id:"+id);
        Session oneSession=null;
        try {
            oneSession = sessionPool.get(id);
        }catch (NullPointerException e){
            //get不到就会空指针异常
            e.printStackTrace();
        }

        if (oneSession != null) {
            try {
                oneSession.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{

        }
    }

}

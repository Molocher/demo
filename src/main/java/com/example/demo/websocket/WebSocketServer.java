package com.example.demo.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: demo
 * Created by moloq on 2021/5/24 17:02
 */
@Component
@ServerEndpoint("/websocket/server/{userId}")
public class WebSocketServer {

    private static AtomicInteger onlineCount = new AtomicInteger(0);

    private static ConcurrentHashMap<String, WebSocketServer> webSoketMap = new ConcurrentHashMap<>();

    private Session session;

    private String userId = "";

    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) {
        this.session = session;
        this.userId = userId;
        if (webSoketMap.containsKey(userId)) {
            webSoketMap.remove(userId);
            webSoketMap.put(userId, this);
        } else {
            webSoketMap.put(userId, this);
            addOnlineCount();
        }

        System.out.println("用户连接：" + userId + ",当前在线人数为:" + getOnlineCount());

        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose() {
        if (webSoketMap.containsKey(userId)) {
            webSoketMap.remove(userId);
            subOnlineCount();
        }

        System.out.println("用户退出:" + userId + ",当前在线人数:" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("用户消息:" + userId + ",报文" + message);
    }


    /**
     * 发送自定义消息
     */
    public static void sendInfo(String message, @PathParam("userId") String userId) throws IOException {
        webSoketMap.get(userId).sendMessage(message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("用户错误:" + this.userId + "原因:" + error.getMessage());
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public int getOnlineCount() {
        return onlineCount.get();
    }

    public void addOnlineCount() {
        WebSocketServer.onlineCount.getAndIncrement();
    }

    public void subOnlineCount() {
        WebSocketServer.onlineCount.getAndDecrement();
    }
}

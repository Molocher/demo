package com.example.demo.websocket;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

/**
 * Description: demo
 * Created by moloq on 2021/5/24 17:51
 */
@Component
public class SubscribeListener implements MessageListener {

    private Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {

    }


}

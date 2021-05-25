package com.example.demo.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Description: demo
 * Created by moloq on 2021/5/24 17:00
 */
@Component
public class RedisUtil {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public void publish(String key,String value){
        stringRedisTemplate.convertAndSend(key,value);
    }
}

package com.example.demo.websocket;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * Description: demo
 * Created by moloq on 2021/5/25 9:28
 */
@Controller
public class DemoController {

    @GetMapping("/index")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("请求成功");
    }

    @GetMapping("/page")
    public String page() {
        System.out.println("hello");
        return "websocket";
    }

    @RequestMapping("/push/{toUserId}")
    public ResponseEntity<String> pushToWeb(String message, @PathVariable String toUserId) throws IOException {
            WebSocketServer.sendInfo(message, toUserId);
            return ResponseEntity.ok("MSG SEND SUCCESS");
    }
}

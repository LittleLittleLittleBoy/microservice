package cn.edu.nju.candleflame.microservice.cloud_client_feign.controller;

import cn.edu.nju.candleflame.microservice.cloud_client_feign.server.ServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自己写的调用服务的代码，为了方便便写成了可以通过url访问的形式
 */
@RestController
public class ServerController {
    @Autowired
    ServerClient serverClient;

    @RequestMapping(value = "hello")
    public String sayHello(@RequestParam("param")String param){
        return serverClient.hello(param);
    }
}

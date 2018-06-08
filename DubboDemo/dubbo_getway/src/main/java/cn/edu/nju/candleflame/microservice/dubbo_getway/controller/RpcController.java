package cn.edu.nju.candleflame.microservice.dubbo_getway.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.IHelloService;

@RestController
public class RpcController {
    @Reference
    private IHelloService hello;
    @RequestMapping(value = "/")
    public String say(){
        return hello.say("rpc");
    }
}

package cn.edu.nju.candleflame.microservice.cloud_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务注册的代码与SpringMVC的controller无区别
 */
@RestController
public class ServiceController {
    @RequestMapping(value = "hello")
    public String hello(@RequestParam("param")String param){
        return "rpc: "+param;
    }
}

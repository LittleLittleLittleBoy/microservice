package cn.edu.nju.candleflame.microservice.cloud_client_ribbon.controller;

import cn.edu.nju.candleflame.microservice.cloud_client_ribbon.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

    @Autowired
    private BusinessService businessService;
    /**
     * 使用rabbion调用远程服务
     *
     * @return
     */
    @RequestMapping(value = "ribbon")
    public String sayRabbion() {
        return businessService.say();
    }

}

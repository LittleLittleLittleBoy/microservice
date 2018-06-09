package cn.edu.nju.candleflame.microservice.cloud_client_feign.server;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "SERVER")
public interface ServerClient {
    @RequestMapping(value = "hello")
    public String hello(@RequestParam("param") String param);
}

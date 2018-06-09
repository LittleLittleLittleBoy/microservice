package cn.edu.nju.candleflame.microservice.cloud_client_ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * 调用服务
 */
public class BusinessService {
    @Autowired
    RestTemplate restTemplate;

    /**
     * 服务具体所在地址及传递的参数
     * 与 URL 不同的是
     * 原本主机名＋端口的地址变为服务名称 SERVER ，
     * 由此可以推断出 Ribbon 客户端根据服务名称从 Eureka 注册中心寻找具体服务地址。
     * 在有多个服务提供者的时候由Eureka注册中心的服务列表与Ribbon配合完成负载均衡
     * @return
     */
    public String say(){
        return restTemplate.getForObject("http://SERVER/hello?param=cloud",String.class);
    }
}

package cn.edu.nju.candleflame.microservice.cloud_client_ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 调用服务
 */
@Component
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
    //Ribbon调用服务发生错误的时候，熔断机制生效，调用由fallbackMethod指定的方法而不是抛出异常，使整个系统能够继续运行
    @HystrixCommand(fallbackMethod = "fallback")
    public String say(){
        return restTemplate.getForObject("http://SERVER/hello?param=cloud",String.class);
    }

    public String fallback(){
        return "容错数据";
    }

}

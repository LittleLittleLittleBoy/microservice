package cn.edu.nju.candleflame.microservice.dubbo_client.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import service.IHelloService;

@Component
public class InvokeService {

    /**
     * 用于生成远程服务代理
     */
    @Reference
    public IHelloService hello;

}

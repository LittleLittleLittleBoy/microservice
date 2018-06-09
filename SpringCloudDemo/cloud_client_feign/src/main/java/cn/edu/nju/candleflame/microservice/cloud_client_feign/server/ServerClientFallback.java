package cn.edu.nju.candleflame.microservice.cloud_client_feign.server;

import org.springframework.stereotype.Component;

@Component
public class ServerClientFallback implements ServerClient {
    @Override
    public String hello(String param) {
        return "容错数据";
    }
}

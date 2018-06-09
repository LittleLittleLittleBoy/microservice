package cn.edu.nju.candleflame.microservice.cloud_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 开启服务发现与注册支持
 * 开启 DiscoveryC!ient 的实例 ，
 * 与 Eureka Server 进行交互,
 * 负责注册服务、租约续期、检索服务、取消租约等功能。
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CloudServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudServiceApplication.class, args);
    }
}

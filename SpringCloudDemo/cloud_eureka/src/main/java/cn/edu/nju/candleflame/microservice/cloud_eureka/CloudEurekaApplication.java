package cn.edu.nju.candleflame.microservice.cloud_eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 开启 Eureka 服务
 * 通过该注解将本应用变为 Eureka 服务器。
 */
@SpringBootApplication
@EnableEurekaServer
public class CloudEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudEurekaApplication.class, args);
    }
}

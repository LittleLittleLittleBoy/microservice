package cn.edu.nju.candleflame.microservice.cloud_client_ribbon;

import cn.edu.nju.candleflame.microservice.cloud_client_ribbon.config.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 每一个SpringCloud应用都需要引入Eureka Discovery 来接入到 Eureka 运行环境中，
 * 所以需要在SpringBoot的入口处通过＠EnableDiscoveryClient注解添加发现服务能力
 * 并且在 application.properties中配置Eureka地址、应用名称、访问端口等基本信息。
 */

/**
 * EnableCircuitBreaker 开启熔断器
 */

@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
/**
 * 使用RibbonClients注解对所有服务的负载策略配置生效
 */
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class CloudClientRibbonApplication {

    /**
     * 注入RestTemplate示例
     * RestTemplate通过Spring自带的Rest客户端，可以方便的发起HTTP请求并且将结果序列化
     * LoadBalanced开启Ribbon的负载均衡模式，开启后Ribbon将拦截 RestTemplate 发起的请求，井实现负载均衡
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(CloudClientRibbonApplication.class, args);
    }
}

package cn.edu.nju.candleflame.microservice.cloud_getway;

import cn.edu.nju.candleflame.microservice.cloud_getway.configuration.RequestFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * EnableZuulProxy 开启Zuul支持，设置一个Zuul的服务端点，并开启反向代理过滤器
 * 以达到将请求转发到后端的服务提供方的目的
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class CloudGetwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudGetwayApplication.class, args);
    }

    /**
     * 注入过滤器让他生效
     * @return
     */
    @Bean
    public RequestFilter logFilter(){
        return new RequestFilter();
    }
}

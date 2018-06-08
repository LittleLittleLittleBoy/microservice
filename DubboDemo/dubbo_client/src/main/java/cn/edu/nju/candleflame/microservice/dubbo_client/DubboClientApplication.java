package cn.edu.nju.candleflame.microservice.dubbo_client;

import cn.edu.nju.candleflame.microservice.dubbo_client.service.impl.InvokeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DubboClientApplication {

    /**
     * 调用Dubbo服务，依次启动zookeeper 、服务方、消费方
     * @param args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DubboClientApplication.class, args);
        InvokeService service = run.getBean(InvokeService.class);
        System.out.println("收到返回结果："+service.hello.say("rpc"));
    }
}

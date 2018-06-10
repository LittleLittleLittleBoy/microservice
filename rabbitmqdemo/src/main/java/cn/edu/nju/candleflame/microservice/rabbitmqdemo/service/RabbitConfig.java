package cn.edu.nju.candleflame.microservice.rabbitmqdemo.service;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue mqQueue() {
        return new Queue("mq");
    }
}

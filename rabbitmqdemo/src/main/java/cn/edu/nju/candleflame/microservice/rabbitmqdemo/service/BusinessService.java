package cn.edu.nju.candleflame.microservice.rabbitmqdemo.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sender(){
        rabbitTemplate.convertAndSend("mq","message content");
    }
}

package cn.edu.nju.candleflame.microservice.rabbitmqdemo.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "mq")
public class MQReceiver {

    @RabbitHandler
    public void process(String message){
        System.out.println("Message is :"+message);
    }
}

package cn.edu.nju.candleflame.microservice.rabbitmqdemo;

import cn.edu.nju.candleflame.microservice.rabbitmqdemo.service.BusinessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageTest {

    @Autowired
    BusinessService businessService;

    @Test
    public void sendMessage(){

        businessService.sender();

    }
}

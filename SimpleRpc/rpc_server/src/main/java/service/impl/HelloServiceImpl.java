package service.impl;

import service.HelloService;

public class HelloServiceImpl implements HelloService {
    public String hello(String name) {
        System.out.println("server:"+"收到消息"+name);
        return "Hello "+name;
    }
}

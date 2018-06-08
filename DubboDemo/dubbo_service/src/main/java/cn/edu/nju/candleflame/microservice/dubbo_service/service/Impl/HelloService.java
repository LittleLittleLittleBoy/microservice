package cn.edu.nju.candleflame.microservice.dubbo_service.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import service.IHelloService;

/**
 * 用于暴露服务
 */
@Service(mock = "cn.edu.nju.candleflame.microservice.dubbo_service.service.Impl.HelloServiceMock")
public class HelloService implements IHelloService {

    @Override
    public String say(String msg) {
        System.out.println(msg);
        return "你好：" + msg;
    }

}

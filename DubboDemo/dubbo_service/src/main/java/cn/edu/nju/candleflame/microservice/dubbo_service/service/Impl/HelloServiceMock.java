package cn.edu.nju.candleflame.microservice.dubbo_service.service.Impl;

import service.IHelloService;

/**
 * 降级的类 使用的时候需要在@Service使用的时候添加mock的内容，同时在服务调用方，配置注解＠ Reference 设置 mock 为 true 开启降级。
 */
public class HelloServiceMock implements IHelloService {
    @Override
    public String say(String msg) {
        return "降级数据";
    }
}

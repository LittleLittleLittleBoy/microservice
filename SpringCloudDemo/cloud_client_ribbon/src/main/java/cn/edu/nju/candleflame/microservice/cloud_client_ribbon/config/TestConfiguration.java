package cn.edu.nju.candleflame.microservice.cloud_client_ribbon.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * 针对某一具体服务配置负载策略，可以使用＠RibbonClient注解单独配置
 *
 * name 为服务名称，configuration 为负载策略配置文件。
 */
@Configuration
@RibbonClient(name = "SERVER",configuration = RibbonConfiguration.class)
public class TestConfiguration {
}

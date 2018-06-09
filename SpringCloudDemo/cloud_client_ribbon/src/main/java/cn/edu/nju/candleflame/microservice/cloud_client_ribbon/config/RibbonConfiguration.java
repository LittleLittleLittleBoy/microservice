package cn.edu.nju.candleflame.microservice.cloud_client_ribbon.config;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置Ribbon负载均衡策略
 */
@Configuration
public class RibbonConfiguration {

    /**
     * Ribbon 提供多种负载策略， 由 IRule 进行管理。
     * 通过继承ClientConfigEnabledRoundRobinRule可自定义负载策略。
     * @return
     */
    @Bean
    public IRule ribbonRule(){
        //最大可用策略 先过滤出故障服务器，然后选择一个当前兵法请求数最小的
        return new BestAvailableRule();
    }
}

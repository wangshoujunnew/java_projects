package cn.itcast.order;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//负载均衡配置类
@Configuration
public class RibbonClientConfiguration {

    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }

}

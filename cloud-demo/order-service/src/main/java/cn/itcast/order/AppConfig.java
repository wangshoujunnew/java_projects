package cn.itcast.order;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// Spring Boot 应用程序在启动过程中会扫描所有的类路径以及其他相关配置文件，以便找到所有被 @Configuration 注解的类，并将其作为配置类进行加载
// AppConfig要和Application放在同一个类路径下
@Configuration
public class AppConfig {
    //@LoadBalance 注解是 Spring Cloud Ribbon 提供的负载均衡注解，用于将一个 RestTemplate 对象设置为 Ribbon 负载均衡客户端，以便在请求服务提供方时自动进行负载均衡
    //    在SpringCloud中,有两种调用服务时客户端的负载均衡策略,一个是Ribbon,一个是Feign
//
//            Ribbon是一个基于Http端的负载均衡,通过在Configuration中配置RestTemplate来进行调用,可以自定义负载均衡的方式
//
//    Feign是一个通过本地接口的形式来进行调用服务的,其中Feign中默认引入了Ribbon,在线上开发中,我还是比较倾向用Feign,
//
//    因为Feign中以接口的形式进行调用服务,看起来简洁,而且Feign中还可以增加熔断器,来进行服务的熔断和降级,防止服务调用中的服务的雪崩
    // @LoadBalanced 要放在bean前面
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

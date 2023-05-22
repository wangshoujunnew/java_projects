package cn.itcast.order.service;

import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    //这里的注入，必领在配置类创建了才能注入，具体在哪个配置类创建，见main方法那个类(springBoot主启动类）
//    @Bean 和 @Autowired 的 关系
//    @Bean 和 @Autowired 做了两件完全不同的事情：
//
//    @Bean 告诉 Spring：“这是这个类的一个实例，请保留它，并在我请求时将它还给我”。
//    @Autowired 说：“请给我一个这个类的实例，例如，一个我之前用@Bean注释创建的实例”。
    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    public void testInit(){
        System.out.println("OrderService init #########---->####");
    }


    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = new Order(); //  orderMapper.findById(orderId);
        order.setUserId(1L);
        // restTemplate远程调用
        //2、利用restTemplate发起http请求，查动用户

        // 没有注册eruka的时候
        String url = "http://localhost:8081/user/" + order.getUserId();

        // 注册eruka的时候
        // LoadBalanced必须要给restTemplate加上注解才能通过userserver服务名访问
        // 负载均衡算法, 1. 随机算法 2. 轮询算法
        url = "http://userserver/user/" + order.getUserId();
        System.out.println("url=" + url);
        User user = restTemplate.getForObject(url, User.class);//发送http动求,第二个参数是预期返回类型,可以返回json也可以返回对象
        System.out.printf("user.username=%s, user.address=%s", user.getUsername(), user.getAddress());
        // 4.返回
        order.setName("test");
        return order;
    }
}

package cn.itcast.order.web;

import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import cn.itcast.order.service.OrderService;
import cn.itcast.order.service.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
/**
@SpringBootApplication
该注解是Spring Boot项目的入口注解，用于标记主类，并且包含了@Configuration、@EnableAutoConfiguration和@ComponentScan等注解。
@RestController
该注解用于标记一个控制器类，并且默认情况下该类中所有的方法都会以JSON格式返回数据。
@RequestMapping
该注解用于映射HTTP请求到控制器的具体方法或类上，可以指定请求路径、请求方法、请求参数等。
@RequestParam
该注解用于获取HTTP请求中的参数值，并且可以指定参数名称、是否必填、默认值等。
@PathVariable
该注解用于获取URL路径中的变量值，并且可以指定变量名称、是否必填、默认值等。
@Autowired
该注解用于自动注入依赖，在Spring容器中查找与类型相匹配的Bean，并将其注入到当前类中。
@Service
该注解用于标记一个服务类，通常与@Autowired一起使用。
@Repository
该注解用于标记一个数据访问对象（DAO），通常与@Autowired一起使用。
@Component
该注解用于标记一个通用组件，通常作为其他注解的父注解。
@Configuration
该注解用于标记一个配置类，通常包含多个Bean的定义。
@Value
该注解用于获取配置文件中的属性值，并可以指定默认值。
@Async
该注解用于标记异步方法。
@EnableScheduling
该注解用于启用Spring的定时任务功能。
@Scheduled
该注解用于定义定时任务的执行周期和具体操作。
 */

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId) {
        // 根据id查询订单并返回
        return orderService.queryOrderById(orderId);
    }

    @GetMapping("/testDebug")
    public String testDebug() {
        // 根据id查询订单并返回
        String out = "StringIndex11112####";
        return out;
    }

    @GetMapping("/shiwu")
    public String shiwu(){
        // 事务
        return "ok";
    }

    @PostConstruct
    public void init() {
        System.out.println("------->init ######");
    }




    @Autowired
    private UserServiceClient userServiceClient;

    @GetMapping("testFeign/{userId}")
    public Order testFeign(@PathVariable("userId") Long userId) {
        // 测试Feign的调用
        User user = userServiceClient.queryById(1L);
        System.out.println(userId + ":user.username=" + user.getUsername());
        return new Order();
    }
}

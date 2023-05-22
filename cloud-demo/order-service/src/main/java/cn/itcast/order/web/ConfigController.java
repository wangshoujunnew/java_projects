package cn.itcast.order.web;

import cn.itcast.order.expr.Expr1;
import cn.itcast.order.utils.Limiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//配置查询控制器
@RestController
@RequestMapping(value = "/config")
@RefreshScope // 实时刷新配置信息
public class ConfigController {
    /**
     * 读取person.name配置信息
     */
    @Value(value = "${person.name:}")
    private String userName;
    /**
     * 读取person.age配置信息
     */
    @Value(value = "${person.age:}")
    private String userAge;

    @Autowired
    private Expr1 expr1;

    // 限流器
    private final Limiter rateLimiter = new Limiter(10, 1); // 定义一个 QPS 为 1 的限流器

    /**
     * 获取配置内容
     *
     * @return
     */
    @RequestMapping(value = "/get")
    public String getConfig() {
        if(!rateLimiter.tryAcquire()){
            return "请求过于频繁";
        }
        System.out.println(expr1.getUserName());
        return userName + ":" + userAge;
    }

}

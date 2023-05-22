package cn.itcast.order.service;

import cn.itcast.order.RibbonClientConfiguration;
import cn.itcast.order.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//调用其他服务的接口, 配置文件生效的优先级似乎要大一些
@FeignClient(name = "userserver", configuration = RibbonClientConfiguration.class)
public interface UserServiceClient {
    @GetMapping("user/{id}")
    User queryById(@PathVariable("id") Long id);

}

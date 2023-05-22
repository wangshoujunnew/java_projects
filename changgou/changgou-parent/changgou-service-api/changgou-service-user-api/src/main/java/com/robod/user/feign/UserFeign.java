package com.robod.user.feign;

import com.robod.entity.Result;
import com.robod.user.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Robod
 * @date 2020/8/21 21:30
 */
@FeignClient("user")
@RequestMapping("/user")
public interface UserFeign {

    /***
     * 根据ID查询User数据
     * @param id
     * @return
     */
    @GetMapping({"/load/{id}"})
    Result<User> findById(@PathVariable String id);

    /**
     * 添加积分
     * @param points
     * @return
     */
    @PostMapping("/addPoints")
    Result addPoints(@RequestParam("points") int points);

}

package com.robod.exception;

import com.robod.entity.Result;
import com.robod.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Robod
 * @date 2020/7/2 21:35
 */
@ControllerAdvice // 在不同的控制器中对同一种异常进行统一处理。
public class BaseExceptionHandler {

    /***
     * 异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class) // 全局异常捕获的类型
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
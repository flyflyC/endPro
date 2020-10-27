package com.vedu.servicebase.exceptionhandler;

import com.vedu.commonutils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: guli-parent
 * @description:统一异常处理类
 * @author: Mr.Wang
 * @create: 2020-10-16 19:07
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 统一异常处理类
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error().message("执行了全局定义异常");
    }

    /**
     * 特殊异常
     * @param e
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        e.printStackTrace();;
        return Result.error().message("执行了特定异常");
    }

    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(EduException.class)
    @ResponseBody
    public Result error(EduException e){
        log.error(e.getMessage()); // 将错误日志输出到文件
        e.printStackTrace();
        return Result.error().code(e.getCode()).message(e.getMsg());
    }

    /*try {
        int num = 10/0;
    }catch (Exception e){
        throw new GuliException(20001,"除数不能为零");
    }*/
}


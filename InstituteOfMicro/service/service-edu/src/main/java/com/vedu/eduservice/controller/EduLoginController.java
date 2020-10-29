package com.vedu.eduservice.controller;

import com.vedu.common.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2020-10-17 19:12
 **/
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin // 解决跨域请求
public class EduLoginController {

    @PostMapping("login")
    public Result login(){

        return Result.ok().data("token","admin");
    }
    @GetMapping("info")
    public Result info(){

        return Result.ok().data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}

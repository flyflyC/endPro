package com.vedu.controller;


import com.vedu.common.JwtUtils;
import com.vedu.common.Result;
import com.vedu.common.ordervo.UcenterMemberOrder;
import com.vedu.entity.UcenterMember;
import com.vedu.entity.vo.RegisterVo;
import com.vedu.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-10-20
 */
@RestController
@RequestMapping("/member")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    //登录
    @PostMapping("/login")
    public Result loginUser(@RequestBody UcenterMember member){
        String token = memberService.login(member);
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",token);
        return Result.ok().data(map);
    }
    //注册
    @PostMapping("/register")
    public Result registerUser(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return Result.ok();
    }

    @GetMapping("/getMemberInfo")
    public Result getMemberInfo(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = memberService.getById(memberId);
        HashMap<String,Object> map = new HashMap<>();
        map.put("userInfo",member);
        return Result.ok().data(map);
    }

    //根据用户id获取用户信息
    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id){
        UcenterMember member = memberService.getById(id);
        //把member的信息赋值给UcenterMemberOrder
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member,ucenterMemberOrder);
        return ucenterMemberOrder;
    }
}


package com.vedu.controller;

import com.vedu.common.Result;
import com.vedu.service.MsmService;
import com.vedu.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/msm")
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //发送短信
    @GetMapping("/send/{phone}")
    public Result sendMsm(@PathVariable String phone){
        //从redis获取验证码,如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)){
            return Result.ok();
        }
        //redis获取不到使用阿里云发送
        //生成验证码
        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        System.out.println(code);
        //发送短信
        Boolean isSend=msmService.send(param,phone);
        System.out.println(isSend);
        if(isSend){
            //发送成功,存入redis，设置有效时间
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return  Result.ok();
        }else {
            return Result.error().message("短信发送失败");
        }
    }
}

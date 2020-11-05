package com.vedu.client;

import com.vedu.common.ordervo.UcenterMemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name = "service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {
    //根据用户id获取用户信息
    @PostMapping("/member/getUserInfoOrder/{id}")
    public UcenterMemberVo getUserInfoOrder(@PathVariable("id") String id);
}

package com.vedu.client;

import com.vedu.common.ordervo.UcenterMemberVo;
import org.springframework.stereotype.Component;

@Component
public class UcenterClientImpl implements UcenterClient{
    @Override
    public UcenterMemberVo getUserInfoOrder(String id) {
        System.out.println("Ucenter服务异常！");
        return null;
    }
}

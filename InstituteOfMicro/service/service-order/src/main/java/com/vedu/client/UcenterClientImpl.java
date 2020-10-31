package com.vedu.client;

import com.vedu.common.ordervo.UcenterMemberOrder;
import org.springframework.stereotype.Component;

@Component
public class UcenterClientImpl implements UcenterClient{
    @Override
    public UcenterMemberOrder getUserInfoOrder(String id) {
        System.out.println("Ucenter服务异常！");
        return null;
    }
}

package com.vedu.eduservice.client;

import org.springframework.stereotype.Component;

@Component
public class OrdersClientImpl implements OrdersClient {
    @Override
    public boolean isBuyCourse(String courseId, String memberId) {
        System.out.println("订单异常");
        return false;
    }
}

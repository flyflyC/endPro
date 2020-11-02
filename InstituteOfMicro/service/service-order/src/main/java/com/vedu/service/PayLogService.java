package com.vedu.service;

import com.vedu.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author cc
 * @since 2020-10-30
 */
public interface PayLogService extends IService<PayLog> {

    Map createNative(String orderNo);

    //查询订单支付状态
    Map<String, String> queryPayStatus(String orderNo);

    //修改支付状态
    void updateOrderStatus(Map<String, String> map);
}

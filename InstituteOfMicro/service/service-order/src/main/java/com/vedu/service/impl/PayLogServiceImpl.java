package com.vedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wxpay.sdk.WXPayUtil;
import com.vedu.entity.Order;
import com.vedu.entity.PayLog;
import com.vedu.mapper.PayLogMapper;
import com.vedu.service.OrderService;
import com.vedu.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author cc
 * @since 2020-10-30
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

    @Autowired
    private OrderService orderService;
    @Override
    public Map createNative(String orderNo) {
        try{
            //根据订单号查询订单信息
            QueryWrapper<Order> wrapper = new QueryWrapper<Order>();
            wrapper.eq("order_no",orderNo);
            Order order = orderService.getOne(wrapper);
            //使用map设置生成二维码需要的参数
            Map m = new HashMap();
            //1、设置支付参数
            m.put("appid", "wx74862e0dfcf69954");
            m.put("mch_id", "1558950191");
            m.put("nonce_str", WXPayUtil.generateNonceStr());
            m.put("body", order.getCourseTitle());
            m.put("out_trade_no", orderNo);
            m.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");
            m.put("spbill_create_ip", "127.0.0.1");
            m.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify\n");
            m.put("trade_type", "NATIVE");
            //发送httpclient请求，传递参数xml格式

            //得到请求结果
        }catch (Exception e){

        }
        return null;
    }
}

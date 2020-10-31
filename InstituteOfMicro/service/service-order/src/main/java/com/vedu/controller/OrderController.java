package com.vedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vedu.common.JwtUtils;
import com.vedu.common.Result;
import com.vedu.entity.Order;
import com.vedu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author cc
 * @since 2020-10-30
 */
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    //订单
    @PostMapping("createOrder/{courseId}")
    public Result saveOrder(@PathVariable String courseId, HttpServletRequest request){
        String orderNo = orderService.createOrders(courseId,JwtUtils.getMemberIdByJwtToken(request));
        HashMap<String,Object> map = new HashMap<>();
        map.put("orderId",orderNo);
        return  Result.ok().data(map);
    }

    //根据订单id查询订单信息
    @GetMapping("getOrderInfo/{orderId}")
    public Result getOrderInfo(@PathVariable String orderId){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        Order order = orderService.getOne(wrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("item",order);
        return Result.ok().data(map);
    }
}


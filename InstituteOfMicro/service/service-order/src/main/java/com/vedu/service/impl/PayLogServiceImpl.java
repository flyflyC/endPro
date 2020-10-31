package com.vedu.service.impl;

import com.vedu.entity.PayLog;
import com.vedu.mapper.PayLogMapper;
import com.vedu.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}

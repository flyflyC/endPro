package com.vedu.staservice.service;

import com.vedu.staservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author lixiande
 * @since 2020-11-01
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    Integer countRegister(String date);

    public void createStatisticsByDay(String day);

    Map<String, Object> getEchartsDate(String begin, String end, String type);
}

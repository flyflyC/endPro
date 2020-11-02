package com.vedu.staservice.mapper;

import com.vedu.staservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 网站统计日数据 Mapper 接口
 * </p>
 *
 * @author lixiande
 * @since 2020-11-01
 */
@Repository
public interface StatisticsDailyMapper extends BaseMapper<StatisticsDaily> {
    public Integer countRegister(String date);
}

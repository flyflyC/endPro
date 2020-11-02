package com.vedu.staservice.service.impl;

import com.alibaba.nacos.client.naming.utils.RandomUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vedu.common.Result;
import com.vedu.staservice.client.UcenterClient;
import com.vedu.staservice.entity.StatisticsDaily;
import com.vedu.staservice.mapper.StatisticsDailyMapper;
import com.vedu.staservice.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author lixiande
 * @since 2020-11-01
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private StatisticsDailyMapper statisticsDailyMapper;

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public void createStatisticsByDay(String day) {
        //删除已存在的统计对象,保证数据库中某一天的数据唯一
        QueryWrapper<StatisticsDaily> dayQueryWrapper = new QueryWrapper<>();
        dayQueryWrapper.eq("date_calculated", day);
        baseMapper.delete(dayQueryWrapper);

        //获取统计信息
        int registerNumber = ucenterClient.getRegisterNumber(day);
        System.out.println(registerNumber);
        Integer registerNum = (Integer) ucenterClient.getRegisterNumber(day);
        Integer loginNum = RandomUtils.nextInt(new Random(100), 200);//TODO
        Integer videoViewNum = RandomUtils.nextInt(new Random(100), 200);//TODO
        Integer courseNum = RandomUtils.nextInt(new Random(100), 200);//TODO

        //创建统计对象
        StatisticsDaily daily = new StatisticsDaily();
        daily.setRegisterNum(registerNum);
        daily.setLoginNum(loginNum);
        daily.setVideoViewNum(videoViewNum);
        daily.setCourseNum(courseNum);
        daily.setDateCalculated(day);

        baseMapper.insert(daily);
    }

    @Override
    public Map<String, Object> getEchartsDate(String begin, String end, String type) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.select(type,"date_calculated");
        wrapper.between("date_calculated",begin,end);

        List<StatisticsDaily> statisticsDailies = baseMapper.selectList(wrapper);

        HashMap<String,Object> hashMap = new HashMap<>();
        List<Integer> dataNum = new ArrayList<>();
        List<String> dataCal = new ArrayList<>();
        hashMap.put("dataNum",dataNum);
        hashMap.put("dataCal",dataCal);

        for (int i = 0; i < statisticsDailies.size(); i++) {
            StatisticsDaily daily = statisticsDailies.get(i);
            dataCal.add(daily.getDateCalculated());
            switch (type){
                case "login_num":
                    dataNum.add(daily.getLoginNum());
                    break;
                case "register_num":
                    dataNum.add(daily.getRegisterNum());
                    break;
                case "video_view_num":
                    dataNum.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    dataNum.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }

        return hashMap;
    }

    @Override
    public Integer countRegister(String date) {
        Integer countRegister = statisticsDailyMapper.countRegister(date);
        return countRegister;
    }
}

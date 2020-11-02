package com.vedu.staservice.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.vedu.common.Result;
import com.vedu.staservice.service.StatisticsDailyService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author lixiande
 * @since 2020-11-01
 */
@RestController
@RequestMapping("/staservice/statistics-daily")
@CrossOrigin
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @GetMapping("getEchartsDate/{begin}/{end}/{type}")
    public Result getEchartsDate(@PathVariable String begin,@PathVariable String end,
                                    @PathVariable String type){
        Map<String,Object> map = statisticsDailyService.getEchartsDate(begin,end,type);
        return Result.ok().data(map);
    }

    @PostMapping("{day}")
    public Result createStatisticsByDate(@PathVariable String day) {
        statisticsDailyService.createStatisticsByDay(day);
        return Result.ok();
    }

    @GetMapping("countRegister/{date}")
    public Result countRegister(@PathVariable String date){
        Integer number =  statisticsDailyService.countRegister(date);
        return Result.ok().data("items",number);
    }
}


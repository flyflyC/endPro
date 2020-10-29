package com.vedu.eduservice.client;

import com.vedu.common.Result;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: InstituteOfMicro
 * @description: 熔断器实现类
 * @author: Mr.LiXianDe
 * @create: 2020-10-27 18:21
 **/
@Component
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public Result removeVideo(String videoId) {
        return Result.error().message("time out");
    }

    @Override
    public Result removeVideoList(List videoIdList) {
        return Result.error().message("time out");
    }
}

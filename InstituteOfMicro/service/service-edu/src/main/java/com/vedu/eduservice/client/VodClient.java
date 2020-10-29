package com.vedu.eduservice.client;

import com.vedu.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program: InstituteOfMicro
 * @description: vod调用客户接口
 * @author: Mr.LiXianDe
 * @create: 2020-10-27 15:07
 **/
// @FeignClient("service-vod")  //未加熔断器之前的注解
@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {
    @DeleteMapping("/admin/vod/video/{videoId}")
    public Result removeVideo(@PathVariable String videoId);

    @DeleteMapping(value = "/admin/vod/video/delete-batch")
    public Result removeVideoList(@RequestParam("videoIdList") List<String> videoIdList);
}

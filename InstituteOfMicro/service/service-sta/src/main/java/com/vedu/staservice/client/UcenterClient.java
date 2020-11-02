package com.vedu.staservice.client;

import com.vedu.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: endPro
 * @description:
 * @author: Mr.LiXianDe
 * @create: 2020-11-01 20:06
 **/
@Component
@FeignClient(name = "service-ucenter",fallback = StaFileDegradeFeignClient.class)
public interface UcenterClient {

    @GetMapping("/member/registerNumber/{date}")
    public int getRegisterNumber(@PathVariable("date") String date);
}

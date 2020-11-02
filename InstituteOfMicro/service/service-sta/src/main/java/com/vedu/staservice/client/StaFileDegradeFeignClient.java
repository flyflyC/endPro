package com.vedu.staservice.client;

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
public class StaFileDegradeFeignClient implements UcenterClient {

    @Override
    public int getRegisterNumber(String date) {
        return -1;
    }
}

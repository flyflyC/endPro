package com.vedu.blog.client;

import com.vedu.common.ordervo.UcenterMemberVo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description
 * @ClassName UcentenFileDegradeFeignClient
 * @Author cai feifei
 * @date 2020.10.19 19:15
 * @Version
 */
@Component
public class UcentenFileDegradeFeignClient implements UcentenClient{
    @Override
    public UcenterMemberVo getUserInfoOrder(@PathVariable String id) {
        return null;
    }
}

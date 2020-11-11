package com.vedu.eduservice.client;

import com.vedu.common.ordervo.UcenterMemberVo;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @ClassName UcentenFileDegradeFeignClient
 * @Author cai feifei
 * @date 2020.10.19 19:15
 * @Version
 */
@Component
public class UcentenFileDegradeFeignClient implements com.vedu.eduservice.client.UcentenClient {
    @Override
    public UcenterMemberVo getUserInfoForCom(String id) {
        return null;
    }
}

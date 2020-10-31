package com.vedu.client;

import com.vedu.common.ordervo.CourseWebVoOrder;
import org.springframework.stereotype.Component;

@Component
public class EduServiceClientImpl implements EduClient{
    @Override
    public CourseWebVoOrder getCourseInfoOrder(String id) {
        System.out.println("edu服务异常！");
        return null;
    }
}

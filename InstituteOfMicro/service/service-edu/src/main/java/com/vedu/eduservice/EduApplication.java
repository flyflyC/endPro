package com.vedu.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2020-10-15 21:38
 **/
@SpringBootApplication
@EnableDiscoveryClient//nacos注册
@EnableFeignClients
@ComponentScan(basePackages = {"com.vedu","com.vedu.eduservice.mapper"}) // 保证本模块外的其他模块也能被扫描到
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}

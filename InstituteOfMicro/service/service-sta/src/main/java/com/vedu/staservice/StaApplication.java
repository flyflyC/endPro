package com.vedu.staservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: endPro
 * @description:
 * @author: Mr.LiXianDe
 * @create: 2020-11-01 19:49
 **/
@SpringBootApplication
@MapperScan("com.vedu.staservice.mapper")
@ComponentScan("com.vedu")
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
public class StaApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaApplication.class,args);
    }
}

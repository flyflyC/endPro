package com.vedu.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description
 * @ClassName BlogServiceApplication
 * @Author cai feifei
 * @date 2020.10.30 16:09
 * @Version
 */
@SpringBootApplication
@ComponentScan({"com.vedu","com.vedu.blog.mapper"})
@EnableDiscoveryClient
@EnableFeignClients
public class BlogServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogServiceApplication.class,args);
    }
}

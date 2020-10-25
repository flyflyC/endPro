package com.vedu.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2020-10-21 16:26
 **/
@SpringBootApplication() // exclude = DataSourceAutoConfiguration.class
@ComponentScan({"com.vedu"})
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }
}

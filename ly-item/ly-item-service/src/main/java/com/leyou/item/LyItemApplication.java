package com.leyou.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author xualin
 * @version v1.0
 * @date 2019/4/2 11:45
 * @description
 **/
@SpringBootApplication
@EnableEurekaClient
public class LyItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(LyItemApplication.class,args);
    }
}

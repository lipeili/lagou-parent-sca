package com.lagou.edu;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@SpringBootApplication
@EntityScan("com.lagou.edu.pojo")
@EnableDiscoveryClient // 开启注册中心客户端 （通用型注解，比如注册到Eureka、Nacos等）
                       // 说明：从SpringCloud的Edgware版本开始，不加注解也ok，但是建议大家加上
public class LagouMailApplication8080 {

    public static void main(String[] args) {
        SpringApplication.run(LagouMailApplication8080.class,args);
    }

}

package com.example.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 订单服务启动类
 * ================================
 * @EnableDiscoveryClient：自己也要注册到 Nacos（网关/别的服务才能找到我）。
 * @EnableFeignClients：打开 Feign 功能 —— 这样 @FeignClient 注解才会生效，
 *                      订单服务才能"像调本地方法一样"调用户服务。
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}

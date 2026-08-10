package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 网关启动类
 * ================================
 * @EnableDiscoveryClient：网关也要注册/发现 ——
 *      关键是"发现"：网关启动时去 Nacos 拿到 user/order 的地址列表，
 *      配置里的 lb://service-user 才能解析成真实地址并转发。
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}

package com.example.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 用户服务启动类
 * ================================
 * @SpringBootApplication：Spring Boot 的"总开关"，启动内置 Tomcat。
 * @EnableDiscoveryClient：打开"服务发现"功能 ——
 *      服务一启动，就会带着自己的名字(service-user)和地址，去 Nacos 登记报到。
 *      这样别的服务就能在 Nacos 里查到"service-user 在哪台机器、哪个端口"。
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserApplication {
    public static void main(String[] args) {
        // 这一行一跑，Tomcat 起来 + 自动向 Nacos 注册自己
        SpringApplication.run(UserApplication.class, args);
    }
}

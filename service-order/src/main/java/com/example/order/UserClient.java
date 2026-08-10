package com.example.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * 用 Feign 声明"怎么调用户服务"
 * ================================
 * 这是 Nacos 版和简化版最关键的区别：
 *
 *   简化版：@FeignClient(name = "service-user", url = "http://localhost:9091")
 *           —— 写死了对方 IP 和端口，用户服务一换机器/端口就挂。
 *
 *   Nacos版：@FeignClient(name = "service-user")
 *           —— 只写"服务名"。地址从 Nacos 注册中心自动查，对方扩缩容/换端口都不用改代码。
 *
 * 你只声明"我要调 service-user 的 GET /user/{id}"，Feign + LoadBalancer 会自动：
 *   1. 去 Nacos 问"service-user 现在在哪台机器、哪个端口"
 *   2. 拼出真实 URL（比如 http://192.168.1.5:9091/user/1）
 *   3. 发 HTTP 请求，把返回的 JSON 转成 Map 给你
 */
@FeignClient(name = "service-user")
public interface UserClient {

    @GetMapping("/user/{id}")
    Map<String, Object> getById(@PathVariable("id") Long id);
}

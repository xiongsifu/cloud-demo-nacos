package com.example.order;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单服务接口
 * ================================
 * 对外提供订单查询。重点：查订单时，要带上"下单用户"的名字，
 * 而用户名在"用户服务"那边 —— 所以这里通过 Feign 远程调用户服务。
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    // Spring 会自动把 UserClient 的实现（Feign 生成的）注入进来
    private final UserClient userClient;

    // 构造器注入（推荐写法）
    public OrderController(UserClient userClient) {
        this.userClient = userClient;
    }

    /**
     * 接口：根据订单号查订单。
     * 访问地址：GET http://localhost:9092/order/1001
     * 内部会远程调用用户服务，把"用户名"一起返回。
     */
    @GetMapping("/{orderNo}")
    public Map<String, Object> getOrder(@PathVariable String orderNo) {
        // 1) 假装从数据库查到一条订单
        Map<String, Object> order = new HashMap<>();
        order.put("orderNo", orderNo);
        order.put("amount", 199.00);
        order.put("status", "已支付");

        // 2) 通过 Feign 远程调用户服务（service-user），拿到用户信息
        //    —— 这里完全没写 IP/端口，Feign 自己从 Nacos 解析
        Map<String, Object> user = userClient.getById(1L);

        // 3) 把用户名塞进订单返回
        order.put("userName", user.get("name"));
        order.put("callUserService", "成功（经 Nacos 服务发现）");
        return order;
    }

    /**
     * 健康检查
     */
    @GetMapping("/ping")
    public String ping() {
        return "service-order 活着，端口 9092";
    }
}

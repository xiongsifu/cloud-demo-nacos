package com.example.user;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户服务接口
 * ================================
 * 提供两个接口：
 *   1) GET /user/{id}   根据 id 查用户（订单服务会远程调用这个）
 *   2) GET /user/ping   健康检查（告诉外界"我还活着"）
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 接口一：根据 id 查用户。
     * 访问地址：GET http://localhost:9091/user/1
     * 返回：{"id":1,"name":"张三","phone":"13800000001"}
     */
    @GetMapping("/{id}")
    public Map<String, Object> getById(@PathVariable Long id) {
        // 这里为了教学，直接写死一条数据（真实项目会查数据库）
        Map<String, Object> user = new HashMap<>();
        user.put("id", id);
        user.put("name", "张三");
        user.put("phone", "13800000001");
        return user;
    }

    /**
     * 接口二：健康检查。
     * 访问地址：GET http://localhost:9091/user/ping
     * 作用：让网关 / 运维 / 监控能确认"这个服务还活着"。
     */
    @GetMapping("/ping")
    public String ping() {
        return "service-user 活着，端口 9091";
    }
}

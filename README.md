# cloud-demo-nacos（接 Nacos 注册中心的微服务版）

> 对应教程：**《后端全栈就业教程_附录_Nacos进阶实战》** + **第 09 册《微服务实战》**
> 这是第 09 册 `cloud-demo` 的「生产真实版」：服务不再写死对方 IP:端口，
> 而是**注册到 Nacos、用服务名互相调用**（订单服务用 `service-user` 这个名字调用户服务，
> 网关用 `lb://service-user` 从 Nacos 解析地址）。

## 技术栈（官方验证过的兼容组合）
- Spring Boot 3.3.4
- Spring Cloud 2023.0.3 + **Spring Cloud Alibaba 2023.0.3.2**
- Nacos 2.4.3（注册中心 / 配置中心）
- OpenFeign（服务间调用）

> ✅ **代码已 Maven 编译通过**（3 个 jar 已在各模块 `target/` 下）。

## ⚠️ 运行前提：先启动 Nacos Server
Nacos Server 是 100MB+ 的独立程序，**不在本仓库里**，需要你本机先准备好：

```bash
# 1) 下载并解压 Nacos 2.4.3（官方：https://github.com/alibaba/nacos/releases）
# 2) 用"单机模式"启动（教学够用）
cd nacos/bin
startup.cmd -m standalone        # Windows
# 或 Linux/Mac: sh startup.sh -m standalone

# 3) 确认 Nacos 控制台能打开：http://localhost:8848/nacos
#    默认账号/密码：nacos / nacos
```

## 启动三个服务
Nacos 起来后，开 3 个终端分别跑（同样注意沙箱的 `SERVER__PORT=0`，加 `--server.port`）：
```bash
java -jar service-user/target/service-user-1.0.0.jar   --server.port=9091
java -jar service-order/target/service-order-1.0.0.jar --server.port=9092
java -jar gateway/target/gateway-1.0.0.jar             --server.port=9090
```

## 验证
```bash
# 服务都注册到 Nacos 了：打开 http://localhost:8848/nacos → 服务管理 → 服务列表
# 调接口（网关→订单→用户 三级调用）：
curl http://localhost:9090/order/1001
# => 含 userName:"张三"，说明网关通过 Nacos 找到了 order，order 通过 Nacos 找到了 user
```

## 和 cloud-demo 的区别
| | cloud-demo（第09册） | cloud-demo-nacos（本版） |
|---|---|---|
| 服务发现 | 写死 IP:端口 | Nacos 自动发现 |
| Feign 调用 | `url=http://localhost:9091` | 只写服务名 `service-user` |
| 网关路由 | `uri: http://localhost:9091` | `uri: lb://service-user` |
| 是否需要 Nacos | 不需要 | **需要** |
| 适合 | 零基础先看懂原理 | 面试/生产真实做法 |

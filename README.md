## 简介
基于Springboot框架实现了一个可以交互的blog网站，前端界面利用Vuejs和ElementUI。应用Redis缓存和用Redis实现一个异步消息队列，项目主要用于学习springboot、redis、mysql实践。

## 项目结构
```
├── back-end -- 后端
|  └── blog -- 后端客户系统
|     ├── src
|     |  ├── main
|     |  |  ├── java-cn-edu-seu-wh-blog
|     |  |  |                 ├── aspect -- 日志切面
|     |  |  |                 ├── async -- 异步消息队列
|     |  |  |                 ├── configuration -- 配置类
|     |  |  |                 ├── controller -- 控制层
|     |  |  |                 ├── dao -- dao层
|     |  |  |                 ├── interceptor -- 拦截器配置
|     |  |  |                 ├── jedis -- jedis配置
|     |  |  |                 ├── mapper -- mapper接口
|     |  |  |                 ├── model -- model层
|     |  |  |                 ├── result -- 返回前端数据封装
|     |  |  |                 ├── service --service层
|     |  |  |                 └── utils --常用工具类
|     |  |  └── resources -- 配置文件和一些静态资源
|     |  └── test
|     └── target
├── front-end -- 前端
|  └── blogapp -- 前端用户界面
|     ├── build 
|     ├── config
|     ├── dist
|     ├── node_modules
|     ├── src
|     |  ├── api -- 接口
|     |  ├── assets -- 静态文件
|     |  ├── components -- 组件
|     |  |  ├── common -- 常用组件
|     |  |  |  ├── carousel
|     |  |  |  ├── markdown --markdown组件
|     |  |  |  ├── mssage -- 消息组件
|     |  |  |  └── scrollPage -- 分页组件
|     |  |  ├── userinfo -- 用户信息组件
|     |  |  ├── userinfonew
|     |  |  └── view -- 视图
|     |  ├── request -- 请求配置
|     |  ├── router
|     |  ├── store
|     |  └── util
|     ├── static
|     └── test        
└── resources -- 资源文件
```

## 技术栈

### 后端技术

技术 | 说明 | 地址
----|----|----
Spring Boot | 新一代 JavaEE 开发标准 | [GitHub](https://github.com/spring-projects/spring-boot)
Spring Cloud Alibaba | 阿里巴巴基于 Spring Cloud 编程模型的微服务生态 | [GitHub](https://github.com/alibaba/spring-cloud-alibaba)
Spring Cloud Alibaba Dubbo | 与 Spring Cloud Alibaba 生态相结合的高性能 Java RPC 框架 | [GitHub](https://github.com/apache/dubbo)
Spring Cloud Alibaba RocketMQ | 分布式消息系统 | [GitHub](https://github.com/alibaba/spring-cloud-alibaba/blob/master/spring-cloud-alibaba-examples/rocketmq-example/readme.md)
Spring Security oAuth2 | 安全认证和授权框架 | [GitHub](https://github.com/spring-projects/spring-security-oauth)
Spring Social | 第三方登录接入框架 | [GitHub](https://github.com/spring-projects/spring-social)
TkMyBatis | 基于 MyBatis 二次开发的轻量级框架，用于简化 MyBatis 操作 | [GitHub](https://github.com/abel533/Mapper)
MyBatisGenerator | Maven 插件，用于 MyBatis 相关代码生成 | [官网](http://www.mybatis.org/generator/)
MybatisCodeHelper | Intellij IDEA 插件，用于 MyBatis 相关代码生成 | [官网](https://plugins.jetbrains.com/plugin/9837-mybatiscodehelperpro)
PageHelper | MyBatis 分页插件 | [GitHub](https://github.com/pagehelper/Mybatis-PageHelper)
Swagger | API 文档生成工具 | [GitHub](https://github.com/swagger-api/swagger-ui)
HikariCP | 数据库连接池 | [GitHub](https://github.com/brettwooldridge/HikariCP)
Docker | 容器化引擎 | [官网](https://www.docker.com/)
Docker Compose | 容器编排工具 | [官网](https://docs.docker.com/compose/)
Mysql | 关系型数据库 | [官网](https://www.mysql.com/cn/)
Redis | 非关系型数据库 | [官网](https://redis.io/)
Nginx | 高性能的HTTP和反向代理web服务器 | [官网](https://www.nginx.com/)

### 前端技术

技术 | 说明 | 地址
----|----|----
Vue | 前端框架，MVVM 模式的实现者 | [GitHub](https://github.com/vuejs/vue)
Vue CLI | Vue 脚手架，基于 NodeJS | [GitHub](https://github.com/vuejs/vue-cli)
Vue Router | Vue 路由框架 | [GitHub](https://github.com/vuejs/vue-router)
Vuex | Vue 全局状态管理框架 | [GitHub](https://github.com/vuejs/vuex)
Axios | 前端 HTTP 框架 | [GitHub](https://github.com/axios/axios)
Element UI | 饿了么 UI 框架 | [官网](https://element.eleme.cn)
Vue Element Admin | 基于 Element UI 的前端后台解决方案 | [GitHub](https://github.com/PanJiaChen/vue-element-admin)
Vue Image Crop Upload | Vue 图片剪裁上传组件 | [GitHub](https://github.com/dai-siki/vue-image-crop-upload)

## 框架集成

集成 | 完成 
----|----
Spring Boot | ✔
Spring Cloud Alibaba | ✔ 
Spring Cloud Alibaba Dubbo | ✔ 
Spring Cloud Alibaba Sentinel | ✔ 
Spring Security oAuth2 | ✔ 
TkMyBatis | ✔ 
HikariCP | ✔ 
OKHttp3 | ✔ 
Feign 传递 Token | ✔ 

## 配置项以及如何启动

### nacos注册中心安装
```
git clone https://github.com/nacos-group/nacos-docker.git
cd nacos-docker
```
将`/resources`目录下的`standalone-mysql.yaml`替换`nacos-docker/example/standalone-mysql.yaml`。

```
# 单机模式运行
docker-compose -f example/standalone-mysql.yaml up -d 
docker-compose -f example/standalone-mysql.yaml logs -f
```

192.168.132.130:8848/nacos account/pass:nacos/nacos

**注意：`standalone-mysql.yaml`修改端口防止端口冲突.**

### 基于docker-compose启动

首先在idea中package springboot项目，然后复制对应jar包到`/resources/docker-compose`目录下，参考ftp-service，进行springboot容器化部署.

```
cd /resources/docker-compose
docker-compose -f docker-compose.yml up -d
```

### Redis安装

```bash
# 运行服务
docker run -it --name redis -v /root/wanghuan/docker/redis/cfg/redis.conf:/usr/local/etc/redis/redis.conf -v /root/wanghuan/docker/redis/data:/data -d -p 6379:6379 redis:latest /bin/bash

# 进入容器
docker exec -it redis bash
# 加载配置
redis-server /usr/local/etc/redis/redis.conf
# 测试连接
redis-cli -a wanghuan
```
> 上述redis.conf文件在resource目录下。

> tips: redis desktop manager for free: https://github.com/qishibo/AnotherRedisDesktopManager/

### Mysql安装

```bash
# pull image
docker pull mysql:latest
# run
docker run --name mysql-mstc -v /root/wanghuan/docker/mysql/data:/data -e MYSQL_ROOT_PASSWORD=123456 -d -i -p 3306:3306 --restart=always  mysql:latest

# 导出sql文件
mysqldump -u root -p seumstc > F:/seumstc.sql
# docker容器内导入sql文件
# 进入docker容器
docker exec -it mysql-mstc bash
# 连接mysql
mysql -u root -p
# 新建数据库
create database seumstc;
use seumstc;
# 导入sql文件
source /data/seumstc.sql;
```

> 上述seumstc.sql文件在resource目录下

> 解决mysql Client does not support authentication protocol requested by server; consider upgrading MySQL错误

```sql
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY '123456';
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '123456';
SELECT plugin FROM mysql.user WHERE User = 'root';
```

### 前端启动

```bash
cd front-end/blogapp
npm i
npm run dev
```

## 技术难点总结

### springboot拦截器配置

### 日志切面配置

### 应用redis作缓存

### 利用redis实现异步消息队列

### 根据功能划分服务

### 利用FeignClient实现RPC通信

### 容器化运行项目

### 负载均衡

## 一些开发中的坑

### feign实现multipartfile上传时出现no multipart boundary was found 问题

在service-provider端重写`RequestInterceptor`中`apply()`方法，添加boundary.
```java
public class MyRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        Map<String, Collection<String>> map = requestTemplate.headers();
        System.out.println(map.get("content-type"));

        String boundary = Long.toHexString(System.currentTimeMillis());
        String contentTypeHeaderValue = new StringBuilder()
                .append("multipart/form-data")
                .append("; charset=UTF-8")
                .append("; boundary=").append(boundary)
                .toString();

        requestTemplate.header("content-type", contentTypeHeaderValue);
    }
```





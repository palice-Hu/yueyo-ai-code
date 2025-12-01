# Yueyor AI Code Mother

<div align="center">

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-green)
![Java](https://img.shields.io/badge/Java-21-blue)
![License](https://img.shields.io/badge/license-Apache%202.0-orange)

一款基于AI的智能代码生成平台，支持多种代码生成方式和自动部署功能。

</div>

## 🌟 项目简介

Yueyor AI Code Mother 是一个智能化代码生成系统，旨在帮助开发者快速生成各种类型的前端应用代码。系统集成了多种AI模型，可以根据用户的需求自动生成HTML单页应用、多文件静态网站以及Vue3项目等多种形式的前端代码。

## 🚀 主要特性

- **多种代码生成模式**：
  - HTML单页应用生成
  - 多文件静态网站生成
  - Vue3项目生成
- **AI智能对话**：通过与AI对话来迭代优化生成的代码
- **自动部署**：一键将生成的应用部署到服务器
- **代码下载**：支持将生成的代码打包下载
- **用户管理系统**：完善的用户注册、登录及权限管理
- **应用管理**：可以创建、编辑、删除和管理自己的应用
- **对话历史记录**：保留与AI的对话历史，方便回溯和继续优化

## 🛠️ 技术栈

### 后端技术
- **核心框架**：Spring Boot 3.5.7
- **编程语言**：Java 21
- **AI集成**：LangChain4j
- **数据库**：MySQL 8.x
- **缓存**：Redis + Caffeine
- **ORM框架**：MyBatis-Flex
- **API文档**：Knife4j (OpenAPI 3)
- **其他组件**：
  - Selenium WebDriver（网页截图）
  - 阿里云DashScope SDK
  - Prometheus监控
  - Redisson分布式锁

### 前端技术
- Vue3 + Vite（前端界面）
- 原生HTML/CSS/JS（代码生成）

## 📁 项目结构

```
yueyor-ai-code-mother/
├── sql/                     # 数据库脚本
├── src/main/java/           # 后端源码
│   ├── com.yueyo.yueyoraicodemother/
│   │   ├── ai/              # AI相关服务
│   │   ├── controller/      # 控制器层
│   │   ├── service/         # 业务逻辑层
│   │   ├── model/           # 数据模型
│   │   ├── mapper/          # 数据访问层
│   │   └── ...              # 其他模块
│   └── dev.langchain4j/     # LangChain4j扩展
├── src/main/resources/      # 配置文件和资源
│   ├── prompt/              # AI提示词模板
│   └── application.yml      # 配置文件
└── yueyor-ai-mother-microservice/  # 微服务模块
    ├── yueyor-ai-code-common/      # 公共模块
    ├── yueyor-ai-code-model/       # 数据模型模块
    └── ...                         # 其他微服务模块
```

## 🔧 环境要求

- JDK 21+
- MySQL 8.x
- Redis 6.x+
- Maven 3.8+
- Node.js 18+（用于Vue项目预览）

## 📦 快速开始

### 1. 数据库初始化

执行 [sql/create_table.sql](sql/create_table.sql) 文件创建数据库和表结构。

### 2. 配置环境

修改 [application-local.yml](src/main/resources/application-local.yml) 文件中的数据库和Redis连接配置：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/yueyor_ai_code_mother
    username: your_username
    password: your_password
  data:
    redis:
      host: localhost
      port: 6379
      password: your_redis_password
```

### 3. 启动项目

```bash
# 使用Maven启动
./mvnw spring-boot:run

# 或者打包后运行
./mvnw clean package
java -jar target/yueyor-ai-code-mother-0.0.1-SNAPSHOT.jar
```

## 📘 API文档

项目启动后访问：http://localhost:8123/api/doc.html 查看API文档。

## 🤖 AI代码生成流程

1. 用户创建应用并输入初始需求描述
2. 系统根据描述自动选择合适的代码生成类型
3. AI根据需求生成相应代码
4. 用户可以通过对话方式优化代码
5. 生成的代码可以部署或下载

## 📊 监控

项目集成了Prometheus监控，可通过 http://localhost:8123/api/actuator/prometheus 访问监控指标。

## 📄 License

本项目采用Apache License 2.0开源协议，详见[LICENSE](LICENSE)文件。
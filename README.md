# 公司官网后端项目

这是一个基于Spring Boot 3和MyBatis-Plus的公司官网后端项目。

## 技术栈

- Spring Boot 3.1.5
- MyBatis-Plus 3.5.3.1
- MySQL 8.0
- Maven

## 功能模块

1. 导航管理
2. 新闻管理
3. 图片管理
4. 联系我们
5. 产品管理

## 项目结构

```
src/main/java/com/company/website/
├── WebsiteApplication.java        # 应用程序入口
├── config/                        # 配置类
├── controller/                    # 控制器
├── service/                       # 服务接口
│   └── impl/                     # 服务实现
├── mapper/                       # MyBatis Mapper
├── entity/                      # 实体类
└── common/                      # 公共组件
    ├── result/                 # 统一返回结果
    └── exception/             # 异常处理
```

## API接口

1. 导航接口
   - GET /api/nav - 获取所有导航信息

2. 新闻接口
   - GET /api/news - 获取新闻列表（分页，每页2条）

3. 图片接口
   - POST /api/img - 根据ID获取图片URL

4. 联系我们接口
   - POST /api/contact - 发送联系表单到邮箱

5. 产品接口
   - GET /api/products - 获取所有产品信息

## 配置说明

1. 数据库配置
   - 在`application.yml`中配置数据库连接信息

2. 邮箱配置
   - 在`application.yml`中配置邮箱服务器信息

## 运行说明

1. 创建数据库并执行`schema.sql`创建表结构
2. 执行`data.sql`插入测试数据
3. 修改`application.yml`中的数据库和邮箱配置
4. 运行`WebsiteApplication.java`启动项目

## 注意事项

1. 请确保MySQL服务已启动
2. 请确保已配置正确的邮箱服务器信息
3. 跨域配置已启用，支持所有域名的访问 
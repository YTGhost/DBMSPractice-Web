# 说明文档

## 项目介绍

采用前后端分离的开发模式，前端发送Ajax请求给后端进行交互，后端不返回页面，只返回Json数据。

前后端之间完全通过public API来约定

该项目的前后端以及数据库均部署在云服务器上，Nginx作为反向代理Web服务器，Tomcat用来部署SSM项目

<img src="http://image.hihia.top/Screenshot/image-20201021113338243.png" alt="image-20201021113338243" style="zoom:50%;" />

## 项目架构

### 前端

前端采用Vue+Element-UI+Axios+Vue-router技术进行编写，实现了SPA（单页面应用）

使用webpack进行前端项目的打包和构建，提高开发效率和项目的可维护性

基于MVVM框架，将重点从如何操作DOM变成了更新JavaScript对象的状态

基于组件化来开发，使得各个模块分离开来，利于团队协作

<img src="http://image.hihia.top/Screenshot/image-20201021120112429.png" alt="image-20201021120112429" style="zoom:50%;" />

### 后端

后端使用SSM(Spring+SpringMVC+Mybatisis)框架进行编写

在云服务器上搭建了多个Tomcat实例进行项目war包的部署

为了跨域开了CORS

![image-20201021120046149](http://image.hihia.top/Screenshot/image-20201021120046149.png)

### Tomcat

使用Tomcat作为WEB服务器，用于部署SSM后端项目war包

![image-20201021115906095](http://image.hihia.top/Screenshot/image-20201021115906095.png)

### Nginx

使用Nginx进行负载均衡（默认的轮询策略）和反向代理

<img src="http://image.hihia.top/Screenshot/image-20201021115511981.png" alt="image-20201021115511981" style="zoom:50%;" />

同时实现了动静分离：将网站静态资源（部署在nginx）与后端项目（部署在tomcat）分离，提高用户访问静态代码的速度，降低对后端应用的访问

<img src="http://image.hihia.top/Screenshot/image-20201021115658474.png" alt="image-20201021115658474" style="zoom:50%;" />

使用反向代理将服务器内后端服务接口与静态资源代理到公网域名上

<img src="http://image.hihia.top/Screenshot/image-20201021115711394.png" alt="image-20201021115711394" style="zoom:50%;" />

### 数据库

使用PostgreSQL作为数据库，数据库同样搭建在云服务器上：

<img src="http://image.hihia.top/Screenshot/image-20201021115417807.png" alt="image-20201021115417807" style="zoom:50%;" />

## 体验地址

前端访问页面： https://www.hihia.top/vue-front-end

后端swagger API文档： https://www.hihia.top/ssm-back-end-api/swagger-ui.html

后端BaseUrl： https://www.hihia.top/ssm-back-end-api/

GitHub地址： https://github.com/YTGhost/DBMSPractice-Web


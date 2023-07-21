# 加代码请加注释,加注释请加代码

#### 要求

* java: 11^
* nodejs: v14^
* postgrs: 12^
* redis-server: 5^

#### 项目结构

* [ruoyi-ui-admin](./ruoyi-ui-admin/README.md) :后台管理模块
* [ruoyi-ui-admin-miniapp](./ruoyi-ui-admin-miniapp/README.md) :后台管理小程序
* [ruoyi-ui-user-miniapp](./ruoyi-ui-user-miniapp/README.md) : 用户端小程序模块
* [ruoyi-common](./ruoyi-common): 公共代码，不包括业务逻辑
* [ruoyi-framework](./ruoyi-framework): 框架代码，业务框架之类
* [ruoyi-starter](./ruoyi-starter/README.md) 程序启动入口代码
* ------- 以下为业务模块
* [ruoyi-system](./ruoyi-system) 系统管理
* [business-member](./business-member): 会员业务模块
* [business-market](./business-market): 主商城模块

#### [ruoyi-starter]层下的ruoyi-api层项目结构分析
```angular2html
1.config层,主要是配置一些工具,例如返回的数据修改封装,安全设置
2.domain层,主要是类似pojo层的功能,设置数据的实体类
3.service层,主要是写的一些服务
4.web层,都是一些controller处理器
```
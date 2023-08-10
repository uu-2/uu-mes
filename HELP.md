# Getting Started

### IDEA 特殊设置

1. 由于使用了 lombok，需要在 IDEA 中安装 lombok 插件，并且在设置中启用 Annotation Processors。
2. 由于使用了 mapstruct，需要在 IDEA 中设置编译选项`Compiler -> Shared build process VM options`: `-Djps.track.ap.dependencies=false`。
3. 配置 IDEA Debug 热启动：HotSwap


### 项目结构规范说明
原则：
1）micro 之间不能互相依赖
2）micro 里面不能包含实际业务逻辑，只提供外部访问端点
3）module 间可以依赖，但是需要注意在财库的情况下，合理设计依赖关系
4）adapter、domain、infra 关系参考六边形框架
5）domain 不能直接以来 infra，需要通过接口和事件模式解耦，达到依赖倒置目的
6）domain 里面不能包含 Converter，Converter 放在各自的 adapter/infra 里面
7）adapter 下面子类型可以自行划分，尽量参考行业最佳实践

.
├── HELP.md       // 开发使用帮助文档
├── README.md     // 项目业务说明文档
├── codegen       // 脚手架 - 代码生产
├── db                   // DB DDL和初始化数据脚本
│   └── init.sql
├── logs                 // 日志目录    
├── components   // 核心组件
│       <root-package>.components.biz    // 业务框架和 DDD 相关的脚手架工具封装
│       <root-package>.components.executor
│       <root-package>.components.json
│       <root-package>.components.syslog
├── facade                   // 服务暴露层
│   ├── facade-admin         
│   │       <root-package>.facade.admin.configuration           // 配置信息
│   │       <root-package>.facade.admin.adapter.listener        // 用户监听组件发出的内存事件。比如：日志、异常等
│   │       <root-package>.facade.admin.adapter.rest            // Http 接口 服务
├── micro                   // 微服务集合，最终物理运行服务。可以根据项目大小合并服务
│   ├── micro-admin         // 后管服务
│   │       <root-package>.micro.admin.MicroAdminApplication
│   │       <root-package>.micro.admin.configuration    // 用户监听组件发出的内存事件。比如：日志、异常等
│   ├── micro-h5            //
│   │       <root-package>.micro.h5.MicroH5Application
│   ├── micro-job/mq        // 可以独立，可以分开
│   │       <root-package>.micro.<?>.MicroJobApplication
│   │       <root-package>.micro.<?>.adapter.listener     // 用户监听组件发出的内存事件。比如：日志、异常等
│   │       <root-package>.micro.<?>.adapter.job          // Job 调用入口
│   │       <root-package>.micro.<?>.adapter.mq           // MQ 监听端点
│   ├── micro-integrated    // 集成服务。包括：开放接口、数据集成等
│   │       <root-package>.micro.inter.MicroInterApplication
│   │       <root-package>.micro.inter.adapter.listener     // 用户监听组件发出的内存事件。比如：日志、异常等
│   │       <root-package>.micro.inter.adapter.rest         // Http方式 集成
│   │       <root-package>.micro.inter.adapter.mq           // 消息方式 集成
│   │       <root-package>.micro.inter.adapter.schedules    // 定时任务 集成
│   ├── micro-<?>           // 任何独立运行服务
│   └── pom.xml
├── module              // 业务领域层
│   ├── module-sdk          // 所有三方系统SDK封装，注意所有 三方系统SDK 都是属于 infra 层
│   │       <root-package>.module.sdk.clients  // 用 forest 封装的外部接口调用API，如：wx、txmap、baidu 等
│   │       <root-package>.module.sdk.utils    // 外部调用时工具处理类
│   ├── module-sys          // 系统核心模块
│   │    方式一：子模块分散（目前选用）
│   │       <root-package>.module.sys.domain                        // 业务领域服务。XxxBiz
│   │       <root-package>.module.sys.domain.entity                 // 业务领域实体。Xxx
│   │       <root-package>.module.sys.domain.points                 // 业务服务所需的外部依赖抽象。如：XxxRepository
│   │       <root-package>.module.sys.infra                         // 外部依赖实现
│   │       <root-package>.module.sys.infra.repository              // 存储接口实现。如：XxxRepositoryImpl
│   │       <root-package>.module.sys.infra.repository.mybatis.dto  // 存储实现的具体方式。如：RDMS、mongo、es
│   │       <root-package>.module.sys.infra.repository.mybatis.mapper
│   │       <root-package>.module.sys.infra.repository.mongo
│   │       <root-package>.module.sys.infra.repository.es
│   │    方式二：子模块独立
│   │       <root-package>.module.sys.dict.<.>
│   │       <root-package>.module.sys.operlog.<.>
│   ├── module-user     // 用户、权限相关模块。包含：运维用户、客户用户等
│   ├── module-...      // 其他业务组件
│   ├── pom.xml
│   └── env             // 环境相关配置
│       ├── docker-compose.yml
│       └── mysql

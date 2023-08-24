-- ----------------------------
-- 2、系统操作日志记录表
-- ----------------------------
drop table if exists sys_log;
CREATE TABLE `t_oper_log`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
    `title`           varchar(255) DEFAULT NULL COMMENT '操作模块',
    `business_type`   int(11) DEFAULT NULL COMMENT '业务类型 "0=其它,1=新增,2=修改,3=删除,4=授权,5=导出,6=导入,7=强退,8=生成代码,9=清空数据"',
    `business_types`  varchar(255) DEFAULT NULL COMMENT '业务类型数组',
    `execute_handler` varchar(255) DEFAULT NULL COMMENT '请求方法',
    `request_method`  varchar(255) DEFAULT NULL COMMENT '请求方式',
    `user_type`       int(11) DEFAULT NULL COMMENT '用户类别  "0=其它,1=后台用户,2=手机端用户"',
    `user_id`         varchar(255) DEFAULT NULL COMMENT '操作人员',
    `user_name`       varchar(255) DEFAULT NULL COMMENT '操作人员名称',
    `dept_name`       varchar(255) DEFAULT NULL COMMENT '部门名称',
    `oper_url`        varchar(255) DEFAULT NULL COMMENT '请求url',
    `oper_ip`         varchar(255) DEFAULT NULL COMMENT '操作地址',
    `oper_location`   varchar(255) DEFAULT NULL COMMENT '操作地点',
    `oper_param`      text COMMENT '请求参数',
    `json_result`     text COMMENT '返回参数',
    `status`          int(11) DEFAULT NULL COMMENT '操作状态（0正常 1异常）',
    `error_msg`       varchar(255) DEFAULT NULL COMMENT '错误消息',
    `oper_time`       datetime     DEFAULT NULL COMMENT '操作时间',
    `cost_time`       bigint(20) DEFAULT NULL COMMENT '消耗时间',
    `api_status`      int(11) DEFAULT NULL COMMENT 'API状态',
    `api_url`         varchar(255) DEFAULT NULL COMMENT 'API地址',
    `api_method`      varchar(255) DEFAULT NULL COMMENT 'API方法',
    `api_cost_time`   bigint(20) DEFAULT NULL COMMENT 'API消耗时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='系统操作日志记录表';

-- ----------------------------
-- 2、系统运营用户信息表
-- ----------------------------
drop table if exists t_oper_user;
create table t_oper_user
(
    user_id        bigint(20) not null auto_increment comment '用户ID',
    bind_id        bigint(20) default 0 comment '绑定真实用户ID',
    user_type      varchar(2)   default '00' comment '用户类型（00系统用户）',
    user_name      varchar(30) not null comment '用户账号',
    nick_name      varchar(30) not null comment '用户昵称',
    email          varchar(50)  default '' comment '用户邮箱',
    phonenumber    varchar(11)  default '' comment '手机号码',
    sex            char(1)      default '0' comment '用户性别（0男 1女 2未知）',
    avatar         varchar(100) default '' comment '头像地址',
    password       varchar(100) default '' comment '密码',
    status         char(1)      default '0' comment '帐号状态（0正常 1停用）',
    del_flag       char(1)      default '0' comment '删除标志（0代表存在 2代表删除）',
    enable_channel varchar(50)  default '' comment '可登录渠道（pc管理后台，mp小程序，）',
    login_ip       varchar(128) default '' comment '最后登录IP',
    login_date     datetime comment '最后登录时间',
    create_by      varchar(64)  default '' comment '创建者',
    create_time    datetime comment '创建时间',
    update_by      varchar(64)  default '' comment '更新者',
    update_time    datetime comment '更新时间',
    remark         varchar(500) default null comment '备注',
    primary key (user_id)
) engine=innodb auto_increment=100 comment = '系统运营用户信息表';


-- ----------------------------
-- 3、参数配置表
-- ----------------------------
drop table if exists t_sys_config;
create table t_sys_config
(
    config_id    int(5) not null auto_increment comment '参数主键',
    config_name  varchar(100) default '' comment '参数名称',
    config_key   varchar(100) default '' comment '参数键名',
    config_value varchar(500) default '' comment '参数键值',
    config_type  char(1)      default 'N' comment '系统内置（Y是 N否）',
    create_by    varchar(64)  default '' comment '创建者',
    create_time  datetime comment '创建时间',
    update_by    varchar(64)  default '' comment '更新者',
    update_time  datetime comment '更新时间',
    remark       varchar(500) default null comment '备注',
    primary key (config_id)
) engine=innodb auto_increment=100 comment = '参数配置表';

insert into t_sys_config
values (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', sysdate(), '', null,
        '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
insert into t_sys_config
values (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', sysdate(), '', null,
        '初始化密码 123456');
insert into t_sys_config
values (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', sysdate(), '', null,
        '深色主题theme-dark，浅色主题theme-light');
insert into t_sys_config
values (4, '账号自助-验证码开关', 'sys.account.captchaEnabled', 'true', 'Y', 'admin', sysdate(), '', null,
        '是否开启验证码功能（true开启，false关闭）');
insert into t_sys_config
values (5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', sysdate(), '', null,
        '是否开启注册用户功能（true开启，false关闭）');
insert into t_sys_config
values (6, '用户登录-黑名单列表', 'sys.login.blackIPList', '', 'Y', 'admin', sysdate(), '', null,
        '设置登录IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）');


-- ----------------------------
-- 4、产线信息表
-- ----------------------------
drop table if exists t_production_line;
create table t_production_line
(
    id          bigint(20) not null auto_increment comment '产线ID',
    line_name   varchar(50) not null comment '产线名称',
    line_code   varchar(50) not null comment '产线编码',
    line_type   varchar(50) not null comment '产线类型',
    line_status varchar(50) not null comment '产线状态',
    line_desc   varchar(50) not null comment '产线描述',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (id)
) engine=innodb comment = '产线信息表';

drop table if exists t_production_line_part;
create table t_production_line_part
(
    id          bigint(20) not null auto_increment comment '产线部件ID',
    line_id     bigint(20) not null comment '产线ID',
    part_name   varchar(50) not null comment '部件名称',
    part_code   varchar(50) not null comment '部件编码',
    part_status varchar(50) not null comment '部件状态',
    part_desc   varchar(50) not null comment '部件描述',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (id)
) engine=innodb comment = '产线部件信息表';
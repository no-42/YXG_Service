-- ----------------------------
-- 1、部门表
-- ----------------------------
drop table if exists sys_dept;
create table sys_dept
(
    dept_id     bigint(20) not null auto_increment comment '部门id',
    parent_id   bigint(20) default 0 comment '父部门id',
    ancestors   varchar(50) default '' comment '祖级列表',
    dept_name   varchar(30) default '' comment '部门名称',
    order_num   int(4) default 0 comment '显示顺序',
    leader      varchar(20) default null comment '负责人',
    phone       varchar(11) default null comment '联系电话',
    email       varchar(50) default null comment '邮箱',
    status      char(1)     default '0' comment '部门状态（0正常 1停用）',
    del_flag    char(1)     default '0' comment '删除标志（0代表存在 2代表删除）',
    create_by   varchar(64) default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64) default '' comment '更新者',
    update_time datetime comment '更新时间',
    primary key (dept_id)
) engine=innodb auto_increment=200 comment = '部门表';

-- ----------------------------
-- 初始化-部门表数据
-- ----------------------------
insert into "system".sys_dept(parent_id, ancestors, name, order_num, leader, phone, email, enable, del_flag, create_by,
                              create_time, update_by, update_time)
values (null, null, '若依科技', 0, '若依', '15888888888', 'ry@qq.com', true, false, 'admin', now(), '', null);

-- ----------------------------
-- 2、用户信息表
-- ----------------------------
drop table if exists sys_user;
create table sys_user
(
    user_id     bigint(20) not null auto_increment comment '用户ID',
    dept_id     bigint(20) default null comment '部门ID',
    user_name   varchar(30) not null comment '用户账号',
    nick_name   varchar(30) not null comment '用户昵称',
    user_type   varchar(2)   default '00' comment '用户类型（00系统用户）',
    email       varchar(50)  default '' comment '用户邮箱',
    phonenumber varchar(11)  default '' comment '手机号码',
    sex         char(1)      default '0' comment '用户性别（0男 1女 2未知）',
    avatar      varchar(100) default '' comment '头像地址',
    password    varchar(100) default '' comment '密码',
    status      char(1)      default '0' comment '帐号状态（0正常 1停用）',
    del_flag    char(1)      default '0' comment '删除标志（0代表存在 2代表删除）',
    login_ip    varchar(128) default '' comment '最后登录IP',
    login_date  datetime comment '最后登录时间',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (user_id)
) engine=innodb auto_increment=100 comment = '用户信息表';

-- ----------------------------
-- 初始化-用户信息表数据
-- ----------------------------
insert into "system".sys_user(dept_id, user_name, nick_name, user_type, email, phone, sex, avatar, password, enable,
                              del_flag, login_ip,
                              login_date, create_by, create_time, update_by, update_time, remark)
values ('091b0a49-e492-4be8-9aa1-5c9425260a46', 'admin', '若依', '00', 'ry@163.com', '15888888888', '1', '',
        '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', true, false, '127.0.0.1', now(), 'admin',
        now(), '', null, '管理员');


-- ----------------------------
-- 3、岗位信息表
-- ----------------------------
drop table if exists sys_post;
create table sys_post
(
    post_id     bigint(20) not null auto_increment comment '岗位ID',
    post_code   varchar(64) not null comment '岗位编码',
    post_name   varchar(50) not null comment '岗位名称',
    post_sort   int(4) not null comment '显示顺序',
    status      char(1)     not null comment '状态（0正常 1停用）',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (post_id)
) engine=innodb comment = '岗位信息表';

-- ----------------------------
-- 初始化-岗位信息表数据
-- ----------------------------
insert into "system".sys_post(code, name, sort, enable, create_by, create_time, update_by, update_time, remark)
values ('ceo', '董事长', 1, true, 'admin', now(), '', null, '');

-- ----------------------------
-- 4、角色信息表
-- ----------------------------
drop table if exists sys_role;
create table sys_role
(
    role_id             bigint(20) not null auto_increment comment '角色ID',
    role_name           varchar(30)  not null comment '角色名称',
    role_key            varchar(100) not null comment '角色权限字符串',
    role_sort           int(4) not null comment '显示顺序',
    data_scope          char(1)      default '1' comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    menu_check_strictly tinyint(1) default 1 comment '菜单树选择项是否关联显示',
    dept_check_strictly tinyint(1) default 1 comment '部门树选择项是否关联显示',
    status              char(1)      not null comment '角色状态（0正常 1停用）',
    del_flag            char(1)      default '0' comment '删除标志（0代表存在 2代表删除）',
    create_by           varchar(64)  default '' comment '创建者',
    create_time         datetime comment '创建时间',
    update_by           varchar(64)  default '' comment '更新者',
    update_time         datetime comment '更新时间',
    remark              varchar(500) default null comment '备注',
    primary key (role_id)
) engine=innodb auto_increment=100 comment = '角色信息表';

-- ----------------------------
-- 初始化-角色信息表数据
-- ----------------------------
insert into "system".sys_role(name, key, sort, data_scope, menu_check_strictly, dept_check_strictly, enable, del_flag,
                              create_by,
                              create_time, update_by, update_time, remark)
values ('超级管理员', 'admin', 1, 1, true, true, true, false, 'admin', now(), '', null, '超级管理员');
insert into "system".sys_role
values ('普通角色', 'common', 2, 2, true, true, true, false, 'admin', now(), '', null, '普通角色');


-- ----------------------------
-- 5、菜单权限表
-- ----------------------------
drop table if exists sys_menu;
create table sys_menu
(
    menu_id     bigint(20) not null auto_increment comment '菜单ID',
    menu_name   varchar(50) not null comment '菜单名称',
    parent_id   bigint(20) default 0 comment '父菜单ID',
    order_num   int(4) default 0 comment '显示顺序',
    path        varchar(200) default '' comment '路由地址',
    component   varchar(255) default null comment '组件路径',
    query       varchar(255) default null comment '路由参数',
    is_frame    int(1) default 1 comment '是否为外链（0是 1否）',
    is_cache    int(1) default 0 comment '是否缓存（0缓存 1不缓存）',
    menu_type   char(1)      default '' comment '菜单类型（M目录 C菜单 F按钮）',
    visible     char(1)      default 0 comment '菜单状态（0显示 1隐藏）',
    status      char(1)      default 0 comment '菜单状态（0正常 1停用）',
    perms       varchar(100) default null comment '权限标识',
    icon        varchar(100) default '#' comment '菜单图标',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default '' comment '备注',
    primary key (menu_id)
) engine=innodb auto_increment=2000 comment = '菜单权限表';

-- ----------------------------
-- 初始化-菜单信息表数据
-- ----------------------------
-- 一级菜单
insert into "system".sys_menu(name, parent_id, order_num, path, component, query, is_frame, is_cache, type, visible,
                              enable,
                              perms, icon, create_by,
                              create_time, update_by, update_time, remark)
values ('系统管理', null, '1', 'system', null, '', true, false, 'M', true, true, '', 'system', 'admin', now(), '', null,
        '系统管理目录');
insert into "system".sys_menu(name, parent_id, order_num, path, component, query, is_frame, is_cache, type, visible,
                              enable,
                              perms, icon, create_by,
                              create_time, update_by, update_time, remark)
values ('系统监控', null, '2', 'monitor', null, '', true, false, 'M', true, false, '', 'monitor', 'admin', now(), '', null,
        '系统监控目录');
insert into "system".sys_menu(name, parent_id, order_num, path, component, query, is_frame, is_cache, type, visible,
                              enable,
                              perms, icon, create_by,
                              create_time, update_by, update_time, remark)
values ('系统工具', null, '3', 'tool', null, '', true, false, 'M', true, false, '', 'tool', 'admin', now(), '', null,
        '系统工具目录');
insert into "system".sys_menu(name, parent_id, order_num, path, component, query, is_frame, is_cache, type, visible,
                              enable,
                              perms, icon, create_by,
                              create_time, update_by, update_time, remark)
values ('若依官网', null, '4', 'http://ruoyi.vip', null, '', false, false, 'M', true, false, '', 'guide', 'admin', now(),
        '',
        null, '若依官网地址');
-- 二级菜单
insert into "system".sys_menu(name, parent_id, order_num, path, component, query, is_frame, is_cache, type, visible, enable,
                     perms, icon, create_by,
                     create_time, update_by, update_time, remark)
values ('用户管理', '64ca2849-2832-4ca0-97a1-6d2d0804ce58', '1', 'user', 'system/user/index', '', true, false, 'C', true,
        true, 'system:user:list', 'user',
        'admin', now(), '', null, '用户管理菜单'),
       ('角色管理', '64ca2849-2832-4ca0-97a1-6d2d0804ce58', '2', 'role', 'system/role/index', '', true, false, 'C', true,
        true, 'system:role:list', 'peoples',
        'admin', now(), '', null, '角色管理菜单'),
('菜单管理', '64ca2849-2832-4ca0-97a1-6d2d0804ce58', '3', 'menu', 'system/menu/index', '', true, false, 'C', true, true, 'system:menu:list', 'tree-table',
    'admin', now(), '', null, '菜单管理菜单'),
('部门管理', '64ca2849-2832-4ca0-97a1-6d2d0804ce58', '4', 'dept', 'system/dept/index', '', true, false , 'C', true, true, 'system:dept:list', 'tree',
    'admin', now(), '', null, '部门管理菜单'),
('岗位管理', '64ca2849-2832-4ca0-97a1-6d2d0804ce58', '5', 'post', 'system/post/index', '', true, false, 'C', true, true, 'system:post:list', 'post',
    'admin', now(), '', null, '岗位管理菜单'),
('字典管理', '64ca2849-2832-4ca0-97a1-6d2d0804ce58', '6', 'dict', 'system/dict/index', '', true, false, 'C', true, true, 'system:dict:list', 'dict',
    'admin', now(), '', null, '字典管理菜单'),
('参数设置', '64ca2849-2832-4ca0-97a1-6d2d0804ce58', '7', 'config', 'system/config/index', '', true, false, 'C', true, true, 'system:config:list', 'edit',
    'admin', now(), '', null, '参数设置菜单'),
('通知公告', '64ca2849-2832-4ca0-97a1-6d2d0804ce58', '8', 'notice', 'system/notice/index', '', true, false, 'C', true, true, 'system:notice:list',
    'message', 'admin', now(), '', null, '通知公告菜单'),
('日志管理', '64ca2849-2832-4ca0-97a1-6d2d0804ce58', '9', 'log', '', '', true, false, 'M', true, true, '', 'log', 'admin', now(), '', null, '日志管理菜单'),
('在线用户', '774bce56-4538-444f-b522-19e2e991d7f5', '1', 'online', 'monitor/online/index', '', true, false, 'C', true, true, 'monitor:online:list',
    'online', 'admin', now(), '', null, '在线用户菜单'),
('定时任务', '774bce56-4538-444f-b522-19e2e991d7f5', '2', 'job', 'monitor/job/index', '', true, false, 'C', true, true, 'monitor:job:list', 'job',
    'admin', now(), '', null, '定时任务菜单'),
('数据监控', '774bce56-4538-444f-b522-19e2e991d7f5', '3', 'druid', 'monitor/druid/index', '', true, false, 'C', true, true, 'monitor:druid:list', 'druid',
    'admin', now(), '', null, '数据监控菜单'),
('服务监控', '774bce56-4538-444f-b522-19e2e991d7f5', '4', 'server', 'monitor/server/index', '', true, false, 'C', true, true, 'monitor:server:list',
    'server', 'admin', now(), '', null, '服务监控菜单'),
('缓存监控', '774bce56-4538-444f-b522-19e2e991d7f5', '5', 'cache', 'monitor/cache/index', '', true, false, 'C', true, true, 'monitor:cache:list', 'redis',
    'admin', now(), '', null, '缓存监控菜单'),
('缓存列表', '774bce56-4538-444f-b522-19e2e991d7f5', '6', 'cacheList', 'monitor/cache/list', '', true, false, 'C', true, true, 'monitor:cache:list',
    'redis-list', 'admin', now(), '', null, '缓存列表菜单'),
('表单构建', '6879065a-fc56-464a-beb7-0ad97b1f883f', '1', 'build', 'tool/build/index', '', true, false, 'C', true, true, 'tool:build:list', 'build',
    'admin', now(), '', null, '表单构建菜单'),
('代码生成', '6879065a-fc56-464a-beb7-0ad97b1f883f', '2', 'gen', 'tool/gen/index', '', true, false, 'C', true, true, 'tool:gen:list', 'code', 'admin',
    now(), '', null, '代码生成菜单'),
('系统接口', '6879065a-fc56-464a-beb7-0ad97b1f883f', '3', 'swagger', 'tool/swagger/index', '', true, false, 'C', true, true, 'tool:swagger:list',
    'swagger', 'admin', now(), '', null, '系统接口菜单');
-- 三级菜单
-- values ( '操作日志', '7b1d358d-a959-447d-bb07-2cd38c36a2da', '1', 'operlog', 'monitor/operlog/index', '', true, false, 'C', true, true, 'monitor:operlog:list',
--          'form', 'admin', now(), '', null, '操作日志菜单'),
--        ( '登录日志', '7b1d358d-a959-447d-bb07-2cd38c36a2da', '2', 'logininfor', 'monitor/logininfor/index', '', true, false, 'C', true, true,
--          'monitor:logininfor:list', 'logininfor', 'admin', now(), '', null, '登录日志菜单');

insert into "system".sys_menu(name, parent_id, order_num, path, component, query, is_frame, is_cache, type, visible, enable,
                              perms, icon, create_by,
                              create_time, update_by, update_time, remark) values
 ('用户查询', '1e6f3e8e-4a51-476c-accf-d52402dcbcbc', '1', '', '', '', true, false, 'F', true, true, 'system:user:query', '#', 'admin', now(), '',
        null, ''),
 ('用户新增', '1e6f3e8e-4a51-476c-accf-d52402dcbcbc', '2', '', '', '', true, false, 'F', true, true, 'system:user:add', '#', 'admin', now(), '',
        null, ''),
('用户修改', '1e6f3e8e-4a51-476c-accf-d52402dcbcbc', '3', '', '', '', true, false, 'F', true, true, 'system:user:edit', '#', 'admin', now(), '',
        null, ''),
 ('用户删除', '1e6f3e8e-4a51-476c-accf-d52402dcbcbc', '4', '', '', '', true, false, 'F', true, true, 'system:user:remove', '#', 'admin', now(), '',
        null, ''),
('用户导出', '1e6f3e8e-4a51-476c-accf-d52402dcbcbc', '5', '', '', '', true, false, 'F', true, true, 'system:user:export', '#', 'admin', now(), '',
        null, ''),
('用户导入', '1e6f3e8e-4a51-476c-accf-d52402dcbcbc', '6', '', '', '',true, false, 'F', true, true, 'system:user:import', '#', 'admin', now(), '',
        null, ''),
 ( '重置密码', '1e6f3e8e-4a51-476c-accf-d52402dcbcbc', '7', '', '', '', true, false, 'F', true, true, 'system:user:resetPwd', '#', 'admin', now(),
        '', null, ''),
 ( '角色查询', '9b054b42-9506-43c9-afbd-bf00230e1f7d', '1', '', '', '', true, false, 'F', true, true, 'system:role:query', '#', 'admin', now(), '',
        null, ''),
('角色新增', '9b054b42-9506-43c9-afbd-bf00230e1f7d', '2', '', '', '', true, false, 'F', true, true, 'system:role:add', '#', 'admin', now(), '',
        null, ''),
 ( '角色修改', '9b054b42-9506-43c9-afbd-bf00230e1f7d', '3', '', '', '', true, false, 'F', true, true, 'system:role:edit', '#', 'admin', now(), '',
        null, ''),
 ('角色删除', '9b054b42-9506-43c9-afbd-bf00230e1f7d', '4', '', '', '', true, false, 'F', true, true, 'system:role:remove', '#', 'admin', now(), '',
        null, ''),
 ('角色导出', '9b054b42-9506-43c9-afbd-bf00230e1f7d', '5', '', '', '', true, false, 'F', true, true, 'system:role:export', '#', 'admin', now(), '',
        null, ''),
 ('菜单查询', 'b43de959-dd4c-43f2-8652-c4229b026fd7', '1', '', '', '', true, false, 'F', true, false, 'system:menu:query', '#', 'admin', now(), '',
        null, ''),
( '菜单新增', 'b43de959-dd4c-43f2-8652-c4229b026fd7', '2', '', '', '', true, false, 'F', true, false, 'system:menu:add', '#', 'admin', now(), '',
        null, ''),
 ('菜单修改', 'b43de959-dd4c-43f2-8652-c4229b026fd7', '3', '', '', '', true, false, 'F', true, true, 'system:menu:edit', '#', 'admin', now(), '',
        null, ''),
 ('菜单删除', 'b43de959-dd4c-43f2-8652-c4229b026fd7', '4', '', '', '', true, false, 'F', true, true, 'system:menu:remove', '#', 'admin', now(), '',
        null, ''),

 ('部门查询', 'f29aff3a-17f0-40e4-aebd-2d9e64cfd0ea', '1', '', '', '', true, false, 'F', true, true, 'system:dept:query', '#', 'admin', now(), '',
        null, ''),
( '部门新增', 'f29aff3a-17f0-40e4-aebd-2d9e64cfd0ea', '2', '', '', '', true, false, 'F', true, true, 'system:dept:add', '#', 'admin', now(), '',
        null, ''),
 ('部门修改', 'f29aff3a-17f0-40e4-aebd-2d9e64cfd0ea', '3', '', '', '', true, false, 'F', true, true, 'system:dept:edit', '#', 'admin', now(), '',
        null, ''),
('部门删除', 'f29aff3a-17f0-40e4-aebd-2d9e64cfd0ea', '4', '', '', '', true, false, 'F', true, true, 'system:dept:remove', '#', 'admin', now(), '',
        null, ''),

 ( '岗位查询', 'bd54cc17-60ad-408a-853f-c333a0b34045', '1', '', '', '', true, false, 'F', true, true, 'system:post:query', '#', 'admin', now(), '',
        null, ''),
( '岗位新增', 'bd54cc17-60ad-408a-853f-c333a0b34045', '2', '', '', '', true, false, 'F', true, true, 'system:post:add', '#', 'admin', now(), '',
        null, ''),
 ( '岗位修改', 'bd54cc17-60ad-408a-853f-c333a0b34045', '3', '', '', '', true, false, 'F', true, true, 'system:post:edit', '#', 'admin', now(), '',
        null, ''),
 ('岗位删除', 'bd54cc17-60ad-408a-853f-c333a0b34045', '4', '', '', '', true, false, 'F', true, true, 'system:post:remove', '#', 'admin', now(), '',
        null, ''),
('岗位导出', 'bd54cc17-60ad-408a-853f-c333a0b34045', '5', '', '', '', true, false, 'F', true, true, 'system:post:export', '#', 'admin', now(), '',
        null, ''),
 ( '字典查询', '2f1f47e0-a193-4980-b2b2-4fbfdc8e7191', '1', '#', '', '', true, false, 'F', true, true, 'system:dict:query', '#', 'admin', now(), '',
        null, ''),
 ( '字典新增', '2f1f47e0-a193-4980-b2b2-4fbfdc8e7191', '2', '#', '', '', true, false, 'F', true, true, 'system:dict:add', '#', 'admin', now(), '',
        null, ''),
( '字典修改', '2f1f47e0-a193-4980-b2b2-4fbfdc8e7191', '3', '#', '', '', true, false, 'F', true, true, 'system:dict:edit', '#', 'admin', now(), '',
        null, ''),
 ( '字典删除', '2f1f47e0-a193-4980-b2b2-4fbfdc8e7191', '4', '#', '', '', true, false, 'F', true, true, 'system:dict:remove', '#', 'admin', now(), '',
        null, ''),
 ('字典导出', '2f1f47e0-a193-4980-b2b2-4fbfdc8e7191', '5', '#', '', '', true, false, 'F', true, true, 'system:dict:export', '#', 'admin', now(), '',
        null, ''),
('参数查询', '7cce11eb-f04f-470c-a968-39fad986b266', '1', '#', '', '', true, false, 'F', true, true, 'system:config:query', '#', 'admin', now(),
        '', null, ''),
 ( '参数新增', '7cce11eb-f04f-470c-a968-39fad986b266', '2', '#', '', '', true, false, 'F', true, true, 'system:config:add', '#', 'admin', now(), '',
        null, ''),
 ( '参数修改', '7cce11eb-f04f-470c-a968-39fad986b266', '3', '#', '', '', true, false, 'F', true, true, 'system:config:edit', '#', 'admin', now(), '',
        null, ''),
 ( '参数删除', '7cce11eb-f04f-470c-a968-39fad986b266', '4', '#', '', '', true, false, 'F', true, true, 'system:config:remove', '#', 'admin', now(),
        '', null, ''),
 ( '参数导出', '7cce11eb-f04f-470c-a968-39fad986b266', '5', '#', '', '', true, false, 'F', true, true, 'system:config:export', '#', 'admin', now(),
        '', null, ''),
 ('公告查询', 'ac99c3af-11c8-4da6-90c6-47f8b81a364f', '1', '#', '', '', true, false, 'F', true, true, 'system:notice:query', '#', 'admin', now(),
        '', null, ''),
('公告新增', 'ac99c3af-11c8-4da6-90c6-47f8b81a364f', '2', '#', '', '', true, false, 'F', true, true, 'system:notice:add', '#', 'admin', now(), '',
        null, ''),
 ('公告修改', 'ac99c3af-11c8-4da6-90c6-47f8b81a364f', '3', '#', '', '', true, false, 'F', true, true, 'system:notice:edit', '#', 'admin', now(), '',
        null, ''),
 ('公告删除', 'ac99c3af-11c8-4da6-90c6-47f8b81a364f', '4', '#', '', '', true, false, 'F', true, true, 'system:notice:remove', '#', 'admin', now(),
        '', null, ''),
 ('操作查询', '1792cd87-4367-4131-84e2-b2dd51adeace', '1', '#', '', '', true, false, 'F', true, true, 'monitor:operlog:query', '#', 'admin', now(),
        '', null, ''),
 ('操作删除', '1792cd87-4367-4131-84e2-b2dd51adeace', '2', '#', '', '', true, false, 'F', true, true, 'monitor:operlog:remove', '#', 'admin', now(),
        '', null, ''),
 ('日志导出', '1792cd87-4367-4131-84e2-b2dd51adeace', '3', '#', '', '', true, false, 'F', true, true, 'monitor:operlog:export', '#', 'admin', now(),
        '', null, ''),
 ('登录查询', 'c12f3fb1-8399-4e78-a16d-4fd0e23d0939', '1', '#', '', '', true, false, 'F', true, true, 'monitor:logininfor:query', '#', 'admin',
        now(), '', null, ''),
 ('登录删除', 'c12f3fb1-8399-4e78-a16d-4fd0e23d0939', '2', '#', '', '', true, false, 'F', true, true, 'monitor:logininfor:remove', '#', 'admin',
     now(), '', null, ''),
 ('日志导出', 'c12f3fb1-8399-4e78-a16d-4fd0e23d0939', '3', '#', '', '', true, false, 'F', true, true, 'monitor:logininfor:export', '#', 'admin',
     now(), '', null, ''),
 ('账户解锁', 'c12f3fb1-8399-4e78-a16d-4fd0e23d0939', '4', '#', '', '', true, false, 'F', true, true, 'monitor:logininfor:unlock', '#', 'admin',
     now(), '', null, ''),
('在线查询', '0da511a2-dd1b-4d7d-ac5c-0b821f6f6866', '1', '#', '', '', true, false, 'F', true, true, 'monitor:online:query', '#', 'admin', now(),
        '', null, ''),
('批量强退', '0da511a2-dd1b-4d7d-ac5c-0b821f6f6866', '2', '#', '', '', true, false, 'F', true, true, 'monitor:online:batchLogout', '#', 'admin',
    now(), '', null, ''),
('单条强退', '0da511a2-dd1b-4d7d-ac5c-0b821f6f6866', '3', '#', '', '', true, false, 'F', true, true, 'monitor:online:forceLogout', '#', 'admin',
    now(), '', null, ''),
 ('任务查询', '17459ab3-6c20-4f58-8cb5-dda432728652', '1', '#', '', '', true, false, 'F', true, true, 'monitor:job:query', '#', 'admin', now(), '',
        null, ''),
 ('任务新增', '17459ab3-6c20-4f58-8cb5-dda432728652', '2', '#', '', '', true, false, 'F', true, true, 'monitor:job:add', '#', 'admin', now(), '',
        null, ''),
 ('任务修改', '17459ab3-6c20-4f58-8cb5-dda432728652', '3', '#', '', '', true, false, 'F', true, true, 'monitor:job:edit', '#', 'admin', now(), '',
        null, ''),
 ('任务删除', '17459ab3-6c20-4f58-8cb5-dda432728652', '4', '#', '', '', true, false, 'F', true, true, 'monitor:job:remove', '#', 'admin', now(), '',
        null, ''),
 ('状态修改', '17459ab3-6c20-4f58-8cb5-dda432728652', '5', '#', '', '', true, false, 'F', true, true, 'monitor:job:changeStatus', '#', 'admin',
     now(), '', null, ''),
('任务导出', '17459ab3-6c20-4f58-8cb5-dda432728652', '6', '#', '', '', true, false, 'F', true, true, 'monitor:job:export', '#', 'admin', now(), '',
        null, ''),
 ('生成查询', '99696d8f-e619-43d0-abe9-3f085ea35a44', '1', '#', '', '', true, false, 'F', true, true, 'tool:gen:query', '#', 'admin', now(), '',
        null, ''),
 ('生成修改', '99696d8f-e619-43d0-abe9-3f085ea35a44', '2', '#', '', '', true, false, 'F', true, true, 'tool:gen:edit', '#', 'admin', now(), '',
        null, ''),
 ('生成删除', '99696d8f-e619-43d0-abe9-3f085ea35a44', '3', '#', '', '', true, false, 'F', true, true, 'tool:gen:remove', '#', 'admin', now(), '',
        null, ''),
 ( '导入代码', '99696d8f-e619-43d0-abe9-3f085ea35a44', '2', '#', '', '', true, false, 'F', true, true, 'tool:gen:import', '#', 'admin', now(), '',
        null, ''),
 ( '预览代码', '99696d8f-e619-43d0-abe9-3f085ea35a44', '4', '#', '', '', true, false, 'F', true, true, 'tool:gen:preview', '#', 'admin', now(), '',
        null, ''),
 ('生成代码', '99696d8f-e619-43d0-abe9-3f085ea35a44', '5', '#', '', '', true, false, 'F', true, true, 'tool:gen:code', '#', 'admin', now(), '',
        null, '');


-- ----------------------------
-- 6、用户和角色关联表  用户N-1角色
-- ----------------------------
drop table if exists sys_user_role;
create table sys_user_role
(
    user_id bigint(20) not null comment '用户ID',
    role_id bigint(20) not null comment '角色ID',
    primary key (user_id, role_id)
) engine=innodb comment = '用户和角色关联表';

-- ----------------------------
-- 初始化-用户和角色关联表数据
-- ----------------------------
insert into sys_user_role
values ('1', '1');
insert into sys_user_role
values ('2', '2');


-- ----------------------------
-- 7、角色和菜单关联表  角色1-N菜单
-- ----------------------------
drop table if exists sys_role_menu;
create table sys_role_menu
(
    role_id bigint(20) not null comment '角色ID',
    menu_id bigint(20) not null comment '菜单ID',
    primary key (role_id, menu_id)
) engine=innodb comment = '角色和菜单关联表';

-- ----------------------------
-- 初始化-角色和菜单关联表数据
-- ----------------------------
insert into sys_role_menu
values ('2', '1');
insert into sys_role_menu
values ('2', '2');
insert into sys_role_menu
values ('2', '3');
insert into sys_role_menu
values ('2', '4');
insert into sys_role_menu
values ('2', '100');
insert into sys_role_menu
values ('2', '101');
insert into sys_role_menu
values ('2', '102');
insert into sys_role_menu
values ('2', '103');
insert into sys_role_menu
values ('2', '104');
insert into sys_role_menu
values ('2', '105');
insert into sys_role_menu
values ('2', '106');
insert into sys_role_menu
values ('2', '107');
insert into sys_role_menu
values ('2', '108');
insert into sys_role_menu
values ('2', '109');
insert into sys_role_menu
values ('2', '110');
insert into sys_role_menu
values ('2', '111');
insert into sys_role_menu
values ('2', '112');
insert into sys_role_menu
values ('2', '113');
insert into sys_role_menu
values ('2', '114');
insert into sys_role_menu
values ('2', '115');
insert into sys_role_menu
values ('2', '116');
insert into sys_role_menu
values ('2', '117');
insert into sys_role_menu
values ('2', '500');
insert into sys_role_menu
values ('2', '501');
insert into sys_role_menu
values ('2', '1000');
insert into sys_role_menu
values ('2', '1001');
insert into sys_role_menu
values ('2', '1002');
insert into sys_role_menu
values ('2', '1003');
insert into sys_role_menu
values ('2', '1004');
insert into sys_role_menu
values ('2', '1005');
insert into sys_role_menu
values ('2', '1006');
insert into sys_role_menu
values ('2', '1007');
insert into sys_role_menu
values ('2', '1008');
insert into sys_role_menu
values ('2', '1009');
insert into sys_role_menu
values ('2', '1010');
insert into sys_role_menu
values ('2', '1011');
insert into sys_role_menu
values ('2', '1012');
insert into sys_role_menu
values ('2', '1013');
insert into sys_role_menu
values ('2', '1014');
insert into sys_role_menu
values ('2', '1015');
insert into sys_role_menu
values ('2', '1016');
insert into sys_role_menu
values ('2', '1017');
insert into sys_role_menu
values ('2', '1018');
insert into sys_role_menu
values ('2', '1019');
insert into sys_role_menu
values ('2', '1020');
insert into sys_role_menu
values ('2', '1021');
insert into sys_role_menu
values ('2', '1022');
insert into sys_role_menu
values ('2', '1023');
insert into sys_role_menu
values ('2', '1024');
insert into sys_role_menu
values ('2', '1025');
insert into sys_role_menu
values ('2', '1026');
insert into sys_role_menu
values ('2', '1027');
insert into sys_role_menu
values ('2', '1028');
insert into sys_role_menu
values ('2', '1029');
insert into sys_role_menu
values ('2', '1030');
insert into sys_role_menu
values ('2', '1031');
insert into sys_role_menu
values ('2', '1032');
insert into sys_role_menu
values ('2', '1033');
insert into sys_role_menu
values ('2', '1034');
insert into sys_role_menu
values ('2', '1035');
insert into sys_role_menu
values ('2', '1036');
insert into sys_role_menu
values ('2', '1037');
insert into sys_role_menu
values ('2', '1038');
insert into sys_role_menu
values ('2', '1039');
insert into sys_role_menu
values ('2', '1040');
insert into sys_role_menu
values ('2', '1041');
insert into sys_role_menu
values ('2', '1042');
insert into sys_role_menu
values ('2', '1043');
insert into sys_role_menu
values ('2', '1044');
insert into sys_role_menu
values ('2', '1045');
insert into sys_role_menu
values ('2', '1046');
insert into sys_role_menu
values ('2', '1047');
insert into sys_role_menu
values ('2', '1048');
insert into sys_role_menu
values ('2', '1049');
insert into sys_role_menu
values ('2', '1050');
insert into sys_role_menu
values ('2', '1051');
insert into sys_role_menu
values ('2', '1052');
insert into sys_role_menu
values ('2', '1053');
insert into sys_role_menu
values ('2', '1054');
insert into sys_role_menu
values ('2', '1055');
insert into sys_role_menu
values ('2', '1056');
insert into sys_role_menu
values ('2', '1057');
insert into sys_role_menu
values ('2', '1058');
insert into sys_role_menu
values ('2', '1059');
insert into sys_role_menu
values ('2', '1060');

-- ----------------------------
-- 8、角色和部门关联表  角色1-N部门
-- ----------------------------
drop table if exists sys_role_dept;
create table sys_role_dept
(
    role_id bigint(20) not null comment '角色ID',
    dept_id bigint(20) not null comment '部门ID',
    primary key (role_id, dept_id)
) engine=innodb comment = '角色和部门关联表';

-- ----------------------------
-- 初始化-角色和部门关联表数据
-- ----------------------------
insert into sys_role_dept
values ('2', '100');
insert into sys_role_dept
values ('2', '101');
insert into sys_role_dept
values ('2', '105');


-- ----------------------------
-- 9、用户与岗位关联表  用户1-N岗位
-- ----------------------------
drop table if exists sys_user_post;
create table sys_user_post
(
    user_id bigint(20) not null comment '用户ID',
    post_id bigint(20) not null comment '岗位ID',
    primary key (user_id, post_id)
) engine=innodb comment = '用户与岗位关联表';

-- ----------------------------
-- 初始化-用户与岗位关联表数据
-- ----------------------------
insert into sys_user_post
values ('1', '1');
insert into sys_user_post
values ('2', '2');


-- ----------------------------
-- 10、操作日志记录
-- ----------------------------
drop table if exists sys_oper_log;
create table sys_oper_log
(
    oper_id        bigint(20) not null auto_increment comment '日志主键',
    title          varchar(50)   default '' comment '模块标题',
    business_type  int(2) default 0 comment '业务类型（0其它 1新增 2修改 3删除）',
    method         varchar(100)  default '' comment '方法名称',
    request_method varchar(10)   default '' comment '请求方式',
    operator_type  int(1) default 0 comment '操作类别（0其它 1后台用户 2手机端用户）',
    oper_name      varchar(50)   default '' comment '操作人员',
    dept_name      varchar(50)   default '' comment '部门名称',
    oper_url       varchar(255)  default '' comment '请求URL',
    oper_ip        varchar(128)  default '' comment '主机地址',
    oper_location  varchar(255)  default '' comment '操作地点',
    oper_param     varchar(2000) default '' comment '请求参数',
    json_result    varchar(2000) default '' comment '返回参数',
    status         int(1) default 0 comment '操作状态（0正常 1异常）',
    error_msg      varchar(2000) default '' comment '错误消息',
    oper_time      datetime comment '操作时间',
    primary key (oper_id)
) engine=innodb auto_increment=100 comment = '操作日志记录';


-- ----------------------------
-- 11、字典类型表
-- ----------------------------
drop table if exists sys_dict_type;
create table sys_dict_type
(
    dict_id     bigint(20) not null auto_increment comment '字典主键',
    dict_name   varchar(100) default '' comment '字典名称',
    dict_type   varchar(100) default '' comment '字典类型',
    status      char(1)      default '0' comment '状态（0正常 1停用）',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (dict_id),
    unique (dict_type)
) engine=innodb auto_increment=100 comment = '字典类型表';

insert into "system".sys_dict_type(name,type,enable,create_by,create_time,update_by,update_time,remark)
values ('用户性别', 'sys_user_sex', true, 'admin', now(), '', null, '用户性别列表'),
 ('菜单状态', 'sys_show_hide', true, 'admin', now(), '', null, '菜单状态列表'),
 ('系统开关', 'sys_normal_disable', true, 'admin', now(), '', null, '系统开关列表'),
 ('任务状态', 'sys_job_status', true, 'admin', now(), '', null, '任务状态列表'),
 ( '任务分组', 'sys_job_group', true, 'admin', now(), '', null, '任务分组列表'),
 ( '系统是否', 'sys_yes_no', true, 'admin', now(), '', null, '系统是否列表'),
('通知类型', 'sys_notice_type', true, 'admin', now(), '', null, '通知类型列表'),
 ( '通知状态', 'sys_notice_status', true, 'admin', now(), '', null, '通知状态列表'),
 ( '操作类型', 'sys_oper_type', true, 'admin', now(), '', null, '操作类型列表'),
 ( '系统状态', 'sys_common_status', true, 'admin', now(), '', null, '登录状态列表');


-- ----------------------------
-- 12、字典数据表
-- ----------------------------
drop table if exists sys_dict_data;
create table sys_dict_data
(
    dict_code   bigint(20) not null auto_increment comment '字典编码',
    dict_sort   int(4) default 0 comment '字典排序',
    dict_label  varchar(100) default '' comment '字典标签',
    dict_value  varchar(100) default '' comment '字典键值',
    dict_type   varchar(100) default '' comment '字典类型',
    css_class   varchar(100) default null comment '样式属性（其他样式扩展）',
    list_class  varchar(100) default null comment '表格回显样式',
    is_default  char(1)      default 'N' comment '是否默认（Y是 N否）',
    status      char(1)      default '0' comment '状态（0正常 1停用）',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (dict_code)
) engine=innodb auto_increment=100 comment = '字典数据表';

insert into "system".sys_dict_data(sort,label,value,type,css_class,list_class,is_default,enable,create_by,create_time,update_by,update_time,remark)
values ( 1, '男', '0', 'sys_user_sex', '', '', true, true, 'admin', now(), '', null, '性别男'),
 ( 2, '女', '1', 'sys_user_sex', '', '', false, true, 'admin', now(), '', null, '性别女'),
 ( 3, '未知', '2', 'sys_user_sex', '', '', false, true, 'admin', now(), '', null, '性别未知'),
 ( 1, '显示', '0', 'sys_show_hide', '', 'primary', true, true, 'admin', now(), '', null, '显示菜单'),
 ( 2, '隐藏', '1', 'sys_show_hide', '', 'danger', false, true, 'admin', now(), '', null, '隐藏菜单'),
 ( 1, '正常', '0', 'sys_normal_disable', '', 'primary', true, true, 'admin', now(), '', null, '正常状态'),
 (2, '停用', '1', 'sys_normal_disable', '', 'danger', false, true, 'admin', now(), '', null, '停用状态'),
 ( 1, '正常', '0', 'sys_job_status', '', 'primary', true, true, 'admin', now(), '', null, '正常状态'),
  ( 2, '暂停', '1', 'sys_job_status', '', 'danger', false,true, 'admin', now(), '', null, '停用状态'),
 ( 1, '默认', 'DEFAULT', 'sys_job_group', '', '', true, true, 'admin', now(), '', null, '默认分组'),
 ( 2, '系统', 'SYSTEM', 'sys_job_group', '', '', false, true, 'admin', now(), '', null, '系统分组'),
(1, '是', 'Y', 'sys_yes_no', '', 'primary', true, true, 'admin', now(), '', null, '系统默认是'),
 ( 2, '否', 'N', 'sys_yes_no', '', 'danger', false,true, 'admin', now(), '', null, '系统默认否'),
 ( 1, '通知', '1', 'sys_notice_type', '', 'warning', true, true, 'admin', now(), '', null, '通知'),
( 2, '公告', '2', 'sys_notice_type', '', 'success', false, true, 'admin', now(), '', null, '公告'),
 ( 1, '正常', '0', 'sys_notice_status', '', 'primary', true, true, 'admin', now(), '', null, '正常状态'),
 ( 2, '关闭', '1', 'sys_notice_status', '', 'danger', false, true, 'admin', now(), '', null, '关闭状态'),
 ( 99, '其他', '0', 'sys_oper_type', '', 'info', false, true, 'admin', now(), '', null, '其他操作'),
( 1, '新增', '1', 'sys_oper_type', '', 'info', false, true, 'admin', now(), '', null, '新增操作'),
( 2, '修改', '2', 'sys_oper_type', '', 'info', false, true, 'admin', now(), '', null, '修改操作'),
 ( 3, '删除', '3', 'sys_oper_type', '', 'danger', false , true, 'admin', now(), '', null, '删除操作'),
 ( 4, '授权', '4', 'sys_oper_type', '', 'primary', false, true, 'admin', now(), '', null, '授权操作'),
( 5, '导出', '5', 'sys_oper_type', '', 'warning', false, true, 'admin', now(), '', null, '导出操作'),
 ( 6, '导入', '6', 'sys_oper_type', '', 'warning', false, true, 'admin', now(), '', null, '导入操作'),
 (7, '强退', '7', 'sys_oper_type', '', 'danger', false, true, 'admin', now(), '', null, '强退操作'),
 ( 8, '生成代码', '8', 'sys_oper_type', '', 'warning', false, true, 'admin', now(), '', null, '生成操作'),
 ( 9, '清空数据', '9', 'sys_oper_type', '', 'danger', false, true, 'admin', now(), '', null, '清空操作'),
 ( 1, '成功', '0', 'sys_common_status', '', 'primary', false, true, 'admin', now(), '', null, '正常状态'),
 ( 2, '失败', '1', 'sys_common_status', '', 'danger', false, true, 'admin', now(), '', null, '停用状态');


-- ----------------------------
-- 13、参数配置表
-- ----------------------------
drop table if exists sys_config;
create table sys_config
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

insert into "system".sys_config(name,key,value,type,create_by,create_time,update_by,update_time,remark)
values ( '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', now(), '', null,
        '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow'),
('用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', now(), '', null, '初始化密码 123456'),
 ('主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', now(), '', null,
        '深色主题theme-dark，浅色主题theme-light'),
 ('账号自助-验证码开关', 'sys.account.captchaEnabled', 'true', 'Y', 'admin', now(), '', null,
        '是否开启验证码功能（true开启，false关闭）'),
 ('账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', now(), '', null,
        '是否开启注册用户功能（true开启，false关闭）');


-- ----------------------------
-- 14、系统访问记录
-- ----------------------------
drop table if exists sys_logininfor;
create table sys_logininfor
(
    info_id        bigint(20) not null auto_increment comment '访问ID',
    user_name      varchar(50)  default '' comment '用户账号',
    ipaddr         varchar(128) default '' comment '登录IP地址',
    login_location varchar(255) default '' comment '登录地点',
    browser        varchar(50)  default '' comment '浏览器类型',
    os             varchar(50)  default '' comment '操作系统',
    status         char(1)      default '0' comment '登录状态（0成功 1失败）',
    msg            varchar(255) default '' comment '提示消息',
    login_time     datetime comment '访问时间',
    primary key (info_id)
) engine=innodb auto_increment=100 comment = '系统访问记录';


-- ----------------------------
-- 15、定时任务调度表
-- ----------------------------
drop table if exists sys_job;
create table sys_job
(
    job_id          bigint(20) not null auto_increment comment '任务ID',
    job_name        varchar(64)  default '' comment '任务名称',
    job_group       varchar(64)  default 'DEFAULT' comment '任务组名',
    invoke_target   varchar(500) not null comment '调用目标字符串',
    cron_expression varchar(255) default '' comment 'cron执行表达式',
    misfire_policy  varchar(20)  default '3' comment '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
    concurrent      char(1)      default '1' comment '是否并发执行（0允许 1禁止）',
    status          char(1)      default '0' comment '状态（0正常 1暂停）',
    create_by       varchar(64)  default '' comment '创建者',
    create_time     datetime comment '创建时间',
    update_by       varchar(64)  default '' comment '更新者',
    update_time     datetime comment '更新时间',
    remark          varchar(500) default '' comment '备注信息',
    primary key (job_id, job_name, job_group)
) engine=innodb auto_increment=100 comment = '定时任务调度表';

insert into "system".sys_job(name,group,invoke_target,cron_expression,misfire_policy,concurrent,enable,create_by,
                    create_time,update_by,update_time,remark)
values ( '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', now(), '', null,
        ''),
 ( '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams("ry")', '0/15 * * * * ?', '3', '1', '1', 'admin', now(), '',
        null, ''),
 ('系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams("ry", true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3',
        '1', '1', 'admin', now(), '', null, '');


-- ----------------------------
-- 16、定时任务调度日志表
-- ----------------------------
drop table if exists sys_job_log;
create table sys_job_log
(
    job_log_id     bigint(20) not null auto_increment comment '任务日志ID',
    job_name       varchar(64)  not null comment '任务名称',
    job_group      varchar(64)  not null comment '任务组名',
    invoke_target  varchar(500) not null comment '调用目标字符串',
    job_message    varchar(500) comment '日志信息',
    status         char(1)       default '0' comment '执行状态（0正常 1失败）',
    exception_info varchar(2000) default '' comment '异常信息',
    create_time    datetime comment '创建时间',
    primary key (job_log_id)
) engine=innodb comment = '定时任务调度日志表';


-- ----------------------------
-- 17、通知公告表
-- ----------------------------
drop table if exists sys_notice;
create table sys_notice
(
    notice_id      int(4) not null auto_increment comment '公告ID',
    notice_title   varchar(50) not null comment '公告标题',
    notice_type    char(1)     not null comment '公告类型（1通知 2公告）',
    notice_content longblob     default null comment '公告内容',
    status         char(1)      default '0' comment '公告状态（0正常 1关闭）',
    create_by      varchar(64)  default '' comment '创建者',
    create_time    datetime comment '创建时间',
    update_by      varchar(64)  default '' comment '更新者',
    update_time    datetime comment '更新时间',
    remark         varchar(255) default null comment '备注',
    primary key (notice_id)
) engine=innodb auto_increment=10 comment = '通知公告表';

-- ----------------------------
-- 初始化-公告信息表数据
-- ----------------------------
insert into sys_notice
values ('1', '温馨提醒：2018-07-01 若依新版本发布啦', '2', '新版本内容', '0', 'admin', sysdate(), '', null, '管理员');
insert into sys_notice
values ('2', '维护通知：2018-07-01 若依系统凌晨维护', '1', '维护内容', '0', 'admin', sysdate(), '', null, '管理员');


-- ----------------------------
-- 18、代码生成业务表
-- ----------------------------
drop table if exists gen_table;
create table gen_table
(
    table_id          bigint(20) not null auto_increment comment '编号',
    table_name        varchar(200) default '' comment '表名称',
    table_comment     varchar(500) default '' comment '表描述',
    sub_table_name    varchar(64)  default null comment '关联子表的表名',
    sub_table_fk_name varchar(64)  default null comment '子表关联的外键名',
    class_name        varchar(100) default '' comment '实体类名称',
    tpl_category      varchar(200) default 'crud' comment '使用的模板（crud单表操作 tree树表操作）',
    package_name      varchar(100) comment '生成包路径',
    module_name       varchar(30) comment '生成模块名',
    business_name     varchar(30) comment '生成业务名',
    function_name     varchar(50) comment '生成功能名',
    function_author   varchar(50) comment '生成功能作者',
    gen_type          char(1)      default '0' comment '生成代码方式（0zip压缩包 1自定义路径）',
    gen_path          varchar(200) default '/' comment '生成路径（不填默认项目路径）',
    options           varchar(1000) comment '其它生成选项',
    create_by         varchar(64)  default '' comment '创建者',
    create_time       datetime comment '创建时间',
    update_by         varchar(64)  default '' comment '更新者',
    update_time       datetime comment '更新时间',
    remark            varchar(500) default null comment '备注',
    primary key (table_id)
) engine=innodb auto_increment=1 comment = '代码生成业务表';


-- ----------------------------
-- 19、代码生成业务表字段
-- ----------------------------
drop table if exists gen_table_column;
create table gen_table_column
(
    column_id      bigint(20) not null auto_increment comment '编号',
    table_id       varchar(64) comment '归属表编号',
    column_name    varchar(200) comment '列名称',
    column_comment varchar(500) comment '列描述',
    column_type    varchar(100) comment '列类型',
    java_type      varchar(500) comment 'JAVA类型',
    java_field     varchar(200) comment 'JAVA字段名',
    is_pk          char(1) comment '是否主键（1是）',
    is_increment   char(1) comment '是否自增（1是）',
    is_required    char(1) comment '是否必填（1是）',
    is_insert      char(1) comment '是否为插入字段（1是）',
    is_edit        char(1) comment '是否编辑字段（1是）',
    is_list        char(1) comment '是否列表字段（1是）',
    is_query       char(1) comment '是否查询字段（1是）',
    query_type     varchar(200) default 'EQ' comment '查询方式（等于、不等于、大于、小于、范围）',
    html_type      varchar(200) comment '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
    dict_type      varchar(200) default '' comment '字典类型',
    sort           int comment '排序',
    create_by      varchar(64)  default '' comment '创建者',
    create_time    datetime comment '创建时间',
    update_by      varchar(64)  default '' comment '更新者',
    update_time    datetime comment '更新时间',
    primary key (column_id)
) engine=innodb auto_increment=1 comment = '代码生成业务表字段';
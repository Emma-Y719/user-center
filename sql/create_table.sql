-- auto-generated definition
create table user
(
    id            bigint                             not null comment '身份'
        primary key,
    user_role     tinyint  default 0                 not null comment '0-普通用户 1-管理员',
    username      varchar(256)                       null comment '昵称',
    user_account  varchar(256)                       null comment '账号',
    avatar_url    varchar(256)                       null comment '头像',
    gender        tinyint                            null comment '性别',
    user_password varchar(512)                       not null comment '密码',
    phone         varchar(128)                       null comment '电话',
    email         varchar(512)                       null comment '邮箱',
    user_status   tinyint  default 0                 not null comment '状态 0-正常',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint  default 0                 not null comment '是否删除 0-否',
    vip_code      varchar(512)                       null comment '会员码'
);

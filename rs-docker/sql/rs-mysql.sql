
create table account
(
    id          int unsigned auto_increment comment 'id'
        primary key,
    username    varchar(20)        not null comment '用户名',
    password    varchar(20)        not null,
    salt        int default 123456 not null comment '盐值',
    role        int default 1      not null comment '权限',
    update_time datetime           not null comment '更新时间',
    create_time datetime           not null comment '创建时间',
    constraint account_pk
        unique (username)
);


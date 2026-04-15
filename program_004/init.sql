-- ================================================
-- 云端编程 实验三 - 数据库初始化脚本
-- 执行方式：mysql -u root -p < init.sql
-- ================================================

CREATE DATABASE IF NOT EXISTS web_hnust
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

USE web_hnust;

CREATE TABLE IF NOT EXISTS users (
    id         INT          NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    username   VARCHAR(50)  NOT NULL                COMMENT '用户名（唯一）',
    password   VARCHAR(255) NOT NULL                COMMENT '密码',
    email      VARCHAR(100)                         COMMENT '邮箱',
    phone      VARCHAR(20)                          COMMENT '手机号',
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

INSERT IGNORE INTO users (username, password) VALUES ('admin', '$2b$12$1eF2gK3.PHxZ8khgWXpSN.GhD7bcHezyfI5kZBIAo.lnbqsFUPKBC');

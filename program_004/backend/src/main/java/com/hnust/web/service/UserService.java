package com.hnust.web.service;

import com.hnust.web.entity.User;

/**
 * 用户业务逻辑接口
 */
public interface UserService {

    /**
     * 注册新用户
     * @return true 注册成功；false 用户名已存在
     */
    boolean register(String username, String password, String email, String phone);

    /**
     * 验证用户登录
     * @return 登录成功返回用户对象，失败返回 null
     */
    User login(String username, String password);
}

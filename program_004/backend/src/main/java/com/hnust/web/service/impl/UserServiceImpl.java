package com.hnust.web.service.impl;

import com.hnust.web.entity.User;
import com.hnust.web.mapper.UserMapper;
import com.hnust.web.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * UserService 实现类
 * 注入 UserMapper（DAO 层），实现业务逻辑
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserMapper userMapper;

    // 构造器注入（推荐方式）
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean register(String username, String password, String email, String phone) {
        log.debug("注册请求: username={}", username);
        // 检查用户名是否已存在
        if (userMapper.findByUsername(username) != null) {
            log.warn("注册失败：用户名 {} 已存在", username);
            return false;
        }
        
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User(username, hashedPassword, email, phone);
        
        int rows = userMapper.insert(user);
        log.info("注册成功: username={}, id={}", username, user.getId());
        return rows > 0;
    }

    @Override
    public User login(String username, String password) {
        log.debug("登录请求: username={}", username);
        User user = userMapper.findByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            log.info("登录成功: username={}", username);
            return user;
        }
        log.warn("登录失败: username={}", username);
        return null;
    }
}

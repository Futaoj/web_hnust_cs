package com.hnust.web.mapper;

import com.hnust.web.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * DAO 接口 —— MyBatis Mapper，由 MyBatis 自动生成实现类
 * 对应 resources/mapper/UserMapper.xml
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户名查询用户
     */
    User findByUsername(String username);

    /**
     * 插入新用户
     */
    int insert(User user);
}

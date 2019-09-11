package com.ccl.blog.service.impl;

import com.ccl.blog.entity.User;
import com.ccl.blog.mapper.UserMapper;
import com.ccl.blog.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author CCL
 * @date 2019/9/3 13:32
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public User selectOneByAccount(String account) {
        User user = userMapper.selectOneByAccount(account);
        return user;
    }
}





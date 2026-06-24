package com.resume.service.impl;

import com.resume.mapper.UserMapper;
import com.resume.pojo.User;
import com.resume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heber
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByUsernameAndPassword(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username,password);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
}

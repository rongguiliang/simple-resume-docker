package com.resume.service;


import com.resume.pojo.User;

/**
 * @author Heber
 * @version 1.0
 */
public interface UserService {
    User selectByUsernameAndPassword(String username, String password);
    void updateUser(User user); // 添加更新用户方法
}

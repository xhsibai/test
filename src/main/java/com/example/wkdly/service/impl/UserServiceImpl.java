package com.example.wkdly.service.impl;

import com.example.wkdly.entity.User;
import com.example.wkdly.mapper.UserMapper;
import com.example.wkdly.service.UserService;
import com.example.wkdly.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUserByName(String username) {
        User user = userMapper.findUserByName(username);
        return user;
    }

    @Override
    public void register(String username, String password) {
        String md5Password= Md5Util.getMD5String(password);
        userMapper.register(username,md5Password);
    }
}
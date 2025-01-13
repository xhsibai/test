package com.example.wkdly.service.impl;

import com.example.wkdly.entity.User;
import com.example.wkdly.mapper.UserMapper;
import com.example.wkdly.service.UserService;
import com.example.wkdly.utils.Md5Util;
import com.example.wkdly.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

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

    @Override
    public void update(User user) {
        LocalDateTime now = LocalDateTime.now();
        user.setUpdateTime(now);
        userMapper.updateUser(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer id=(Integer)map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePassword(String newPwd) {
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer id=(Integer)map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }
}
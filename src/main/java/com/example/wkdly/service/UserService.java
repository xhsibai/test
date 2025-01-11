package com.example.wkdly.service;
import com.example.wkdly.entity.User;
public interface UserService {
    User findUserByName(String username);

    void register(String username, String password);
}

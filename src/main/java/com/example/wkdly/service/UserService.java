package com.example.wkdly.service;
import com.example.wkdly.entity.User;
public interface UserService {
    User findUserByName(String username);

    void register(String username, String password);
    void update(User user);
    void updateAvatar(String avatarUrl);
    void updatePassword(String newPwd);

}

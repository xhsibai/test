package com.example.wkdly.controller;

import com.example.wkdly.entity.Result;
import com.example.wkdly.entity.User;
import com.example.wkdly.service.UserService;
import com.example.wkdly.utils.JetUtil;
import com.example.wkdly.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        User user=userService.findUserByName(username);
        if(user==null){
            userService.register(username,password);
            return Result.success();
        }else {
            return Result.error("用户已经注册！");

        }
    }
    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username,
                        @Pattern(regexp = "^\\S{5,16}$") String password) {
        User user=userService.findUserByName(username);
        if(user!=null){
            String md5String = Md5Util.getMD5String(password);
            if(user.getPassword().equals(md5String)){
                Map<String,Object> map=new HashMap<>();
                map.put("id",user.getId());
                map.put("username",user.getUsername());
                String jwtToken = JetUtil.getJwtToken(map);
                return Result.success(jwtToken);
            }else {
                return Result.error("密码错误！");
            }
        }else {
            return Result.error("用户不存在！");
        }
    }

    @GetMapping("/info")
    public Result<User>getUserInfo(@RequestHeader("Authorization") String token) {
        Map<String,Object>map= JetUtil.parseToken(token);
        String username=map.get("username").toString();
        User user=userService.findUserByName(username);
        return Result.success(user);
    }
}

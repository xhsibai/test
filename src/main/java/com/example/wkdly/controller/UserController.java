package com.example.wkdly.controller;

import com.example.wkdly.entity.Result;
import com.example.wkdly.entity.User;
import com.example.wkdly.service.UserService;
import com.example.wkdly.utils.JetUtil;
import com.example.wkdly.utils.Md5Util;
import com.example.wkdly.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
       /* Map<String,Object>map= JetUtil.parseToken(token);
        String username=map.get("username").toString();*/
        Map<String,Object>map=ThreadLocalUtil.get();
        String username=map.get("username").toString();
        User user=userService.findUserByName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result updateUserInfo(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }
    @PatchMapping ("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();

    }
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params) {
        String oldPwd=params.get("oldPwd");
        String newPwd=params.get("newPwd");
        String confirmPwd=params.get("confirmPwd");
        if (!StringUtils.hasLength(oldPwd)||!StringUtils.hasLength(newPwd)||!StringUtils.hasLength(confirmPwd)) {
            return Result.error("密码不为空");
        }
        Map<String,Object> map=ThreadLocalUtil.get();
        String username=(String) map.get("username");
        User user=userService.findUserByName(username);
        if (!user.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("原密码错误");
        }
        if (!newPwd.equals(confirmPwd)) {
            return Result.error("两次密码不一致");
        }
        userService.updatePassword(newPwd);
        return Result.success();
    }
}

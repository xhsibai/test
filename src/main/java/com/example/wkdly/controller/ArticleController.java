package com.example.wkdly.controller;

import com.example.wkdly.entity.Result;
import com.example.wkdly.utils.JetUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    /*@GetMapping("/list")
    public Result<String> list(@RequestHeader("Authorization" )String token, HttpServletResponse response) {
        try{
            Map<String,Object> map= JetUtil.parseToken(token);
            return Result.success("文章列表显示...");
        }catch (Exception e){
            response.setStatus(401);
            return Result.error("未登录");
        }
    }*/
    @GetMapping("/list")
    public Result<String> list() {
            return Result.success("文章列表显示...");
    }
}

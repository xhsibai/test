package com.example.wkdly.interceptor;
import com.auth0.jwt.JWT;
import com.example.wkdly.utils.JetUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class Loginterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
        String token=request.getHeader("Authorization");
        try {
            Map<String,Object>map= JetUtil.parseToken(token);
            return true;
        }catch (Exception e){
            response.setStatus(401);
            return false;
        }
    }
}

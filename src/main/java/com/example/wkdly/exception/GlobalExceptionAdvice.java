package com.example.wkdly.exception;

import com.example.wkdly.entity.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception e){
        return Result.error(StringUtils.hasLength(e.getMessage())?e.getMessage():"未知异常");
    }
}
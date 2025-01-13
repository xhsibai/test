package com.example.wkdly.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
public class User {


    @NotNull
    private Integer id;

    private String username;


    @JsonIgnore
    private String password;


    @NotEmpty
    @Pattern(regexp = "^[a-z]{2,10}$",message = "昵称格式不合法！")
    private String nickname;


    @NotEmpty
    @Email
    private String email;


    @URL
    private String userPic;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
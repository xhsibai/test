package com.example.wkdly.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public class Category {
    @NotNull
    private Long id;
    @NotEmpty
    @Pattern(regexp = "^\\s{1,10}$")
    private String categoryName;
    @NotEmpty
    private String categoryAlias;
    private Long createUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}

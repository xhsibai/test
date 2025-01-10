package com.example.wkdly.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

public class Article {

    @NotNull
    private Long id;

    @NotEmpty
    @Pattern(regexp = "^\\s{1,10}$")
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    @URL
    private String coverImg;

    private String state;

    @NotNull
    private Integer categoryId;


    private Integer createUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}

package com.example.wkdly.mapper;

import com.example.wkdly.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{userName}")
    User findUserByName(String userName) ;

    @Insert("insert into user(username,password,create_time,update_time) " +
            "values(#{username},#{md5password},now(),now()) ")
    void register(String username, String md5password);
}

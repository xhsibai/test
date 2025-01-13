package com.example.wkdly.mapper;

import com.example.wkdly.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{userName}")
    User findUserByName(String userName) ;

    @Insert("insert into user(username,password,create_time,update_time) " +
            "values(#{username},#{md5password},now(),now()) ")
    void register(String username, String md5password);

    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    void updateUser(User user);

    @Update("update user set user_pic=#{avatarUrl},update_time=now() where id=#{id}")
    void updateAvatar(String avatarUrl, Integer id);

    @Update("update user set password=#{md5password},update_time=now() where id=#{id}")
    void updatePwd(String md5password, Integer id);


}

package com.lz.shop_mall.mapper;

import com.lz.shop_mall.pojo.User;
import com.lz.shop_mall.pojo.dto.UserDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("insert into user(username, password, create_time, update_time) " +
            " values (#{username},#{password},now(),now())")
    void add(String username, String password);

    @Select("select * from user where username=#{username}")
    User findByUsername(String username);

    @Select("select * from user where user_id = #{id}")
    UserDTO getUserLogin(Integer id);

    @Update("update user set user_pic = #{avatarUrl}, create_time = now() where user_id = #{id}")
    void updateAvatar(String avatarUrl, Integer id);

    // 更新用户信息
    @Update("UPDATE user SET nickname=#{user.nickname}, email=#{user.email}, " +
            "birthday=#{user.birthday}, phone_number=#{user.phoneNumber}, user_pic=#{user.userPic}, sex=#{user.sex}, address=#{user.address}, update_time=now() WHERE user_id=#{userId}")
    void update(@Param("user") User user, @Param("userId") Integer userId);

}

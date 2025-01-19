package com.lz.shop_mall.mapper;

import com.lz.shop_mall.pojo.User;
import com.lz.shop_mall.pojo.dto.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("insert into user(username, password, create_time, update_time) " +
            " values (#{username},#{password},now(),now())")
    void add(String username, String password);

    @Select("select * from user where username=#{username}")
    User findByUsername(String username);

    @Select("select * from user where user_id = #{id}")
    UserDTO getUserLogin(Integer id);
}

package com.lz.shop_mall.mapper;


import com.lz.shop_mall.pojo.UserAddress;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("select * from user_address where user_id = #{id}")
    List<UserAddress> list(Integer id);

    @Update("UPDATE user_address SET default_address = 1 WHERE user_id = #{id}")
    void updateAllAddressToNotDefault(Integer id);

    @Update("UPDATE user_address SET default_address = 0 WHERE user_address_id = #{userAddressId}")
    void updateAddressToDefault(Integer userAddressId);

    @Insert("INSERT INTO user_address (receiver_name, contact, address, user_id, default_address, create_time, update_time) " +
            "VALUES (#{receiverName}, #{contact}, #{address}, #{userId},#{defaultAddress}, #{createTime}, #{updateTime})")
    void add(UserAddress userAddress);

}

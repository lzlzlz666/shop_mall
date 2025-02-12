package com.lz.shop_mall.mapper;


import com.lz.shop_mall.pojo.Order;
import com.lz.shop_mall.pojo.Result;
import com.lz.shop_mall.pojo.UserAddress;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("select * from user_address where user_id = #{id}")
    List<UserAddress> list(Integer id);

    @Update("UPDATE user_address SET default_address = 1 WHERE user_id = #{id}")
    void updateAllAddressToNotDefault(Integer id);

    @Select("select * from user_address where user_address_id = #{userAddressId}")
    UserAddress getAddressByUserAddressId(Integer userAddressId);

    @Update("UPDATE user_address SET default_address = 0 WHERE user_address_id = #{userAddressId}")
    void updateAddressToDefault(Integer userAddressId);

    @Insert("INSERT INTO user_address (receiver_name, contact, address, user_id, default_address, create_time, update_time) " +
            "VALUES (#{receiverName}, #{contact}, #{address}, #{userId},#{defaultAddress}, #{createTime}, #{updateTime})")
    void add(UserAddress userAddress);

    @Select("select * from user_address where user_id = #{id} and default_address = 0")
    Integer getAddressId(Integer id);

    @Insert("INSERT INTO `order` (order_id, user_id, address_id, product_id, product_format, product_count, product_price, is_pay, create_time, update_time) " +
            "VALUES (#{orderId}, #{userId}, #{addressId}, #{productId}, #{productFormat}, #{productCount}, #{productPrice}, #{isPay}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "orderId", keyColumn = "order_id")
    void generateOrder(Order order);

    @Update("UPDATE `order` SET is_pay = 1 WHERE order_id = #{orderId}")
    void purchaseOrder(Integer orderId);

    @Select("select * from `order` where user_id = #{id}")
    List<Order> getOrders(Integer id);

    @Delete("DELETE FROM `order` WHERE order_id = #{orderId} AND user_id = #{userId}")
    void delete(Integer orderId, Integer userId);

    @Delete("delete from user_address where user_address_id = #{id}")
    void deleteAddress(Integer id);

    @Select("select * from `order` where order_id = #{id}")
    Order getOrdersByOrderId(Integer id);
}

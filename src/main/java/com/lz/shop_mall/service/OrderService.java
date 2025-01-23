package com.lz.shop_mall.service;

import com.lz.shop_mall.pojo.Order;
import com.lz.shop_mall.pojo.Result;
import com.lz.shop_mall.pojo.UserAddress;

import java.util.List;

public interface OrderService {
    Result<List<UserAddress>> list();

    Result update(Integer userAddressId);

    Result add(UserAddress userAddress);

    Result<Order> generateOrder(Order order);

    Result purchaseOrder(List<Order> listOrders);
}

package com.lz.shop_mall.controller;


import com.lz.shop_mall.pojo.Order;
import com.lz.shop_mall.pojo.Result;
import com.lz.shop_mall.pojo.UserAddress;
import com.lz.shop_mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public Result<List<UserAddress>> list() {
        return orderService.list();
    }

    @PostMapping("/updateDefaultAddress")
    public Result updateDefaultAddress(@RequestParam Integer userAddressId){
        return orderService.update(userAddressId);
    }

    @PostMapping("/addUserAddress")
    public Result addUserAddress(@RequestBody UserAddress userAddress) {
        return orderService.add(userAddress);
    }

    @PostMapping("/generateOrder")
    public Result<Order> generateOrder(@RequestBody Order order) {
        return orderService.generateOrder(order);
    }

    @PostMapping("/purchaseOrder")
    public Result purchaseOrder(@RequestBody List<Order> listOrders) {
        return orderService.purchaseOrder(listOrders);
    }
}

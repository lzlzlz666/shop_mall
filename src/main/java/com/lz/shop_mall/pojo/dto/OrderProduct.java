package com.lz.shop_mall.pojo.dto;

import com.lz.shop_mall.pojo.Product;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderProduct {

    // 订单ID
    private String id;

    // 订单创建时间
    private LocalDateTime createTime;

    // 订单中的商品列表
    private List<ProductDTO> skus;

    // 订单实付金额
    private double payMoney;

    // 订单邮费
    private double postFee;

    private String address;

    private String contact;

    // 是否已付款
    private Integer paid;
}

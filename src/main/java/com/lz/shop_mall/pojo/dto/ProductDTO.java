package com.lz.shop_mall.pojo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductDTO {
    private Integer productId; // 商品ID
    private Integer categoryId; // 商品分类ID
    private String productName; // 商品名称
    private String productDescription; // 商品描述信息
    private double productOriginalPrice; // 商品原价
    private double productPrice; // 商品现价
    private Integer productStock; // 商品库存
    private Integer productSales; // 商品销量
    private String productImg; // 商品图片
    private Integer quantity; // 商品购买的数量
    private String productFormat; // 产品规格
    private LocalDateTime createTime; // 商品创建时间
    private LocalDateTime updateTime; // 商品更新时间
}

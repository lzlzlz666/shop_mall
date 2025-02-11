package com.lz.shop_mall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Product {
    private Integer productId; // 商品ID
    private Integer categoryId; // 商品分类ID
    private String productName; // 商品名称
    private String productDescription; // 商品描述信息
    private double productOriginalPrice; // 商品原价
    private double productPrice; // 商品现价
    private Integer productStock; // 商品库存
    private Integer productSales; // 商品销量
    private String productImg; // 商品图片
    private LocalDateTime createTime; // 商品创建时间
    private LocalDateTime updateTime; // 商品更新时间

    // 默认忽略 productImgs 字段
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> productImgs;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> productDetailImgs;
}

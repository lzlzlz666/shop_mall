package com.lz.shop_mall.service;

import com.lz.shop_mall.pojo.PageBean;
import com.lz.shop_mall.pojo.Product;
import com.lz.shop_mall.pojo.Result;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();

    List<Product> getHotProduct();

    Result<Product> getProductById(Long id);

    PageBean<Product> list(Integer pageNum, Integer pageSize, Integer categoryId, String productName, Double minPrice, Double maxPrice);
}

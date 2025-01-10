package com.lz.shop_mall.controller;

import com.lz.shop_mall.pojo.Product;
import com.lz.shop_mall.pojo.Result;
import com.lz.shop_mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Result<List<Product>> getAllProducts() {
        List<Product> allProducts = productService.getAllProduct();
        return Result.success(allProducts);
    }

    // 热销商品
    @GetMapping("/hot")
    public Result<List<Product>> getHotProducts() {
        List<Product> HotProducts = productService.getHotProduct();
        return Result.success(HotProducts);
    }

    // 商品详情
    @GetMapping("/{id}")
    public Result<Product> getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

}

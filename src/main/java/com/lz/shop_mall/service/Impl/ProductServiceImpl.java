package com.lz.shop_mall.service.Impl;

import com.lz.shop_mall.mapper.ProductMapper;
import com.lz.shop_mall.pojo.Product;
import com.lz.shop_mall.pojo.Result;
import com.lz.shop_mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 获取所有商品
     * @return
     */
    public List<Product> getAllProduct() {
        return productMapper.list();
    }

    /**
     * 获取商品热销前8商品
     * @return
     */
    public List<Product> getHotProduct() {
        return productMapper.hotList();
    }

    /**
     * 根据id 获取详细商品
     *
     * @param id
     * @return
     */
    public Result getProductById(Long id) {
        Product product = productMapper.getProduct(id);
        if (product == null) {
            return Result.error("未找到商品");
        }
        return Result.success(product);
    }


}

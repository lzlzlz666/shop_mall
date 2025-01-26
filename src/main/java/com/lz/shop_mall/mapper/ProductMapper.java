package com.lz.shop_mall.mapper;

import com.lz.shop_mall.pojo.Product;
import com.lz.shop_mall.pojo.Result;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("select * from product")
    List<Product> list();

    List<Product> hotList();

    @Select("select * from product where product_id = #{id}")
    Product getProduct(Long id);

    List<Product> listByCondition(Integer categoryId, String productName, Double minPrice, Double maxPrice);

    // 根据库存数量进行排序，返回指定数量的商品
    @Select("SELECT * FROM product ORDER BY product_stock DESC LIMIT #{limit}")
    List<Product> getLikeProducts(int limit);
}

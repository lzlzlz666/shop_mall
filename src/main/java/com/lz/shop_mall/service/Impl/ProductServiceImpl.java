package com.lz.shop_mall.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lz.shop_mall.mapper.ProductFormatMapper;
import com.lz.shop_mall.mapper.ProductImgsMapper;
import com.lz.shop_mall.mapper.ProductMapper;
import com.lz.shop_mall.pojo.*;
import com.lz.shop_mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductImgsMapper productImgsMapper;

    @Autowired
    private ProductFormatMapper productFormatMapper;

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

        List<ProductImg> productImgs = productImgsMapper.getProductImgs(id);
        List<ProductDetailImg> productDetailImgs = productImgsMapper.getProductDetailImgs(id);
        List<ProductFormat> productFormats = productFormatMapper.getProductFormatsById(id);

        List<String> formatsList = new ArrayList<>();
        List<String> imgList = new ArrayList<>();
        List<String> detailImgList = new ArrayList<>();

        for (ProductFormat productFormat : productFormats ) {
            formatsList.add(productFormat.getProductFormat());
        }

        for (ProductImg productImg : productImgs) {
            imgList.add(productImg.getProductImg());
        }
        for (ProductDetailImg productDetailImg : productDetailImgs) {
            detailImgList.add(productDetailImg.getProductDetailImg());
        }

        product.setProductFormats(formatsList);
        product.setProductImgs(imgList);
        product.setProductDetailImgs(detailImgList);
        if (product == null) {
            return Result.error("未找到商品");
        }
        return Result.success(product);
    }

    /**
     * 条件分页列表查询
     * @param pageNum
     * @param pageSize
     * @param productName
     * @param minPrice
     * @param maxPrice
     * @return
     */
    public PageBean<Product> list(Integer pageNum, Integer pageSize, Integer categoryId, String productName, Double minPrice, Double maxPrice) {
        // 创建分页Bean对象
        PageBean<Product> pageBean = new PageBean<>();

        // 使用分页查询的插件
        PageHelper.startPage(pageNum, pageSize);

        List<Product> lp = productMapper.listByCondition(categoryId, productName, minPrice, maxPrice);

        Page<Product> p = (Page<Product>) lp;
        // 强转之后调用pageBean的方法
        pageBean.setTotal(p.getTotal());
        pageBean.setItems(p.getResult());
        return pageBean;
    }

    /**
     * 猜你喜欢的商品
     * @param limit
     * @return
     */
    public List<Product> getLikeProducts(int limit) {
        return productMapper.getLikeProducts(limit);
    }


}

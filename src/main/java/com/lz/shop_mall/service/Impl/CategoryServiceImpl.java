package com.lz.shop_mall.service.Impl;

import com.lz.shop_mall.mapper.CategoryMapper;
import com.lz.shop_mall.pojo.Category;
import com.lz.shop_mall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 获取数据库中的所有category的内容（分组的全部内容）
     * @return
     */
    public List<Category> getCategories() {
        return categoryMapper.getCategories();
    }
}

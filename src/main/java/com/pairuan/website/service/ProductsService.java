package com.pairuan.website.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pairuan.website.entity.Products;

import java.util.List;

/**
 * 产品服务接口
 */
public interface ProductsService extends IService<Products> {
    /**
     * 获取所有产品（包含图片URL）
     * @return 产品列表
     */
    List<Products> getAllProductsWithImages();
} 
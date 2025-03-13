package com.pairuan.website.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pairuan.website.entity.Images;
import com.pairuan.website.entity.Products;
import com.pairuan.website.mapper.ImagesMapper;
import com.pairuan.website.mapper.ProductsMapper;
import com.pairuan.website.service.ProductsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 产品服务实现类
 */
@Service
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, Products> implements ProductsService {
    
    @Autowired
    private ImagesMapper imagesMapper;
    
    @Override
    public List<Products> getAllProductsWithImages() {
        // 获取所有产品
        List<Products> productsList = list();
        
        // 获取所有产品涉及的图片ID
        List<Integer> imgIds = productsList.stream()
                .map(Products::getImgId)
                .filter(id -> id != null)
                .distinct()
                .collect(Collectors.toList());
        
        // 批量查询图片
        Map<Integer, Images> imagesMap = new HashMap<>();
        if (!imgIds.isEmpty()) {
            List<Images> images = imagesMapper.selectBatchIds(imgIds);
            imagesMap = images.stream()
                    .collect(Collectors.toMap(Images::getId, Function.identity()));
        }
        
        // 填充产品的图片URL
        Map<Integer, Images> finalImagesMap = imagesMap;
        productsList.forEach(product -> {
            if (product.getImgId() != null && finalImagesMap.containsKey(product.getImgId())) {
                product.setImgUrl(finalImagesMap.get(product.getImgId()).getUrl());
            }
        });
        
        return productsList;
    }
} 
package com.pairuan.website.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pairuan.website.entity.Carousels;
import com.pairuan.website.entity.Images;
import com.pairuan.website.mapper.CarouselsMapper;
import com.pairuan.website.mapper.ImagesMapper;
import com.pairuan.website.service.CarouselsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 轮播图服务实现类
 */
@Service
public class CarouselsServiceImpl extends ServiceImpl<CarouselsMapper, Carousels> implements CarouselsService {
    
    @Autowired
    private ImagesMapper imagesMapper;
    
    @Override
    public List<Carousels> getAllCarouselsWithImages() {
        // 获取所有轮播图
        List<Carousels> carouselsList = list();
        
        // 获取所有轮播图涉及的图片ID
        List<Integer> imgIds = carouselsList.stream()
                .map(Carousels::getImgId)
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
        
        // 填充轮播图的图片URL
        Map<Integer, Images> finalImagesMap = imagesMap;
        carouselsList.forEach(carousel -> {
            if (carousel.getImgId() != null && finalImagesMap.containsKey(carousel.getImgId())) {
                carousel.setImgUrl(finalImagesMap.get(carousel.getImgId()).getUrl());
            }
        });
        
        return carouselsList;
    }
} 
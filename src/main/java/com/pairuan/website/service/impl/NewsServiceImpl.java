package com.pairuan.website.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pairuan.website.entity.Images;
import com.pairuan.website.entity.News;
import com.pairuan.website.mapper.ImagesMapper;
import com.pairuan.website.mapper.NewsMapper;
import com.pairuan.website.service.NewsCategoryRelationsService;
import com.pairuan.website.service.NewsService;

/**
 * 新闻服务实现类
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Autowired
    private NewsCategoryRelationsService newsCategoryRelationsService;
    
    @Autowired
    private ImagesMapper imagesMapper;
    
    @Override
    public Page<News> getNewsPage(Page<News> page) {
        return page(page);
    }
    
    @Override
    public List<News> getAllNewsWithDetails() {
        // 获取所有新闻
        List<News> newsList = list();
        
        // 获取所有新闻涉及的图片ID
        List<Integer> imgIds = newsList.stream()
                .map(News::getImgId)
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
        
        // 填充新闻的分类和图片URL
        Map<Integer, Images> finalImagesMap = imagesMap;
        newsList.forEach(news -> {
            // 设置分类
            List<String> categories = newsCategoryRelationsService.getCategoryNamesByNewsId(news.getId());
            news.setCategory(categories);
            
            // 设置图片URL
            if (news.getImgId() != null && finalImagesMap.containsKey(news.getImgId())) {
                news.setImgUrl(finalImagesMap.get(news.getImgId()).getUrl());
            }
        });
        
        return newsList;
    }
} 
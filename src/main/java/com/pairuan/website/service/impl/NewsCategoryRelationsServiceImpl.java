package com.pairuan.website.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pairuan.website.entity.NewsCategories;
import com.pairuan.website.entity.NewsCategoryRelations;
import com.pairuan.website.mapper.NewsCategoriesMapper;
import com.pairuan.website.mapper.NewsCategoryRelationsMapper;
import com.pairuan.website.service.NewsCategoryRelationsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 新闻分类关系服务实现类
 */
@Service
public class NewsCategoryRelationsServiceImpl extends ServiceImpl<NewsCategoryRelationsMapper, NewsCategoryRelations> implements NewsCategoryRelationsService {
    
    @Autowired
    private NewsCategoriesMapper newsCategoriesMapper;
    
    @Override
    public List<Integer> getCategoryIdsByNewsId(Integer newsId) {
        LambdaQueryWrapper<NewsCategoryRelations> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(NewsCategoryRelations::getNewsId, newsId);
        List<NewsCategoryRelations> relations = list(wrapper);
        return relations.stream()
                .map(NewsCategoryRelations::getCategoryId)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<String> getCategoryNamesByNewsId(Integer newsId) {
        List<Integer> categoryIds = getCategoryIdsByNewsId(newsId);
        if (categoryIds.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<NewsCategories> categories = newsCategoriesMapper.selectBatchIds(categoryIds);
        return categories.stream()
                .map(NewsCategories::getCategoryName)
                .collect(Collectors.toList());
    }
} 
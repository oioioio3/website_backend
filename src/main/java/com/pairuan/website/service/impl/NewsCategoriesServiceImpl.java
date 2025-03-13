package com.pairuan.website.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pairuan.website.entity.NewsCategories;
import com.pairuan.website.mapper.NewsCategoriesMapper;
import com.pairuan.website.service.NewsCategoriesService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 新闻分类服务实现类
 */
@Service
public class NewsCategoriesServiceImpl extends ServiceImpl<NewsCategoriesMapper, NewsCategories> implements NewsCategoriesService {
    
    @Override
    public List<NewsCategories> getAllCategories() {
        return list();
    }
} 
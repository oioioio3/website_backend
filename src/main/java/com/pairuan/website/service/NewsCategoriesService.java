package com.pairuan.website.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pairuan.website.entity.NewsCategories;

import java.util.List;

/**
 * 新闻分类服务接口
 */
public interface NewsCategoriesService extends IService<NewsCategories> {
    /**
     * 获取所有新闻分类
     * @return 新闻分类列表
     */
    List<NewsCategories> getAllCategories();
} 
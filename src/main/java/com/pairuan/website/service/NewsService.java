package com.pairuan.website.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pairuan.website.entity.News;

import java.util.List;

/**
 * 新闻服务接口
 */
public interface NewsService extends IService<News> {
    
    /**
     * 分页查询新闻列表
     * @param page 分页参数
     * @return 分页结果
     */
    Page<News> getNewsPage(Page<News> page);

    /**
     * 获取所有新闻（包含分类和图片URL）
     * @return 新闻列表
     */
    List<News> getAllNewsWithDetails();
} 
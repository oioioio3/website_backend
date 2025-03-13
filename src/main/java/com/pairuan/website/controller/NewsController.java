package com.pairuan.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pairuan.website.common.result.R;
import com.pairuan.website.entity.News;
import com.pairuan.website.service.NewsService;

import java.util.List;

/**
 * 新闻接口
 */
@RestController
@RequestMapping("/api/news")
public class NewsController {
    
    @Autowired
    private NewsService newsService;
    
    /**
     * 获取所有新闻（包含分类和图片URL）
     */
    @GetMapping
    public R<List<News>> getAllNews() {
        List<News> newsList = newsService.getAllNewsWithDetails();
        return R.success(newsList);
    }
} 
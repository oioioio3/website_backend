package com.pairuan.website.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pairuan.website.entity.NewsCategoryRelations;

import java.util.List;

/**
 * 新闻分类关系服务接口
 */
public interface NewsCategoryRelationsService extends IService<NewsCategoryRelations> {
    /**
     * 根据新闻ID获取分类ID列表
     * @param newsId 新闻ID
     * @return 分类ID列表
     */
    List<Integer> getCategoryIdsByNewsId(Integer newsId);
    
    /**
     * 根据新闻ID获取分类名称列表
     * @param newsId 新闻ID
     * @return 分类名称列表
     */
    List<String> getCategoryNamesByNewsId(Integer newsId);
} 
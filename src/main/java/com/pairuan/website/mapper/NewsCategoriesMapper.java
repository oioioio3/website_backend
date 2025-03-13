package com.pairuan.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pairuan.website.entity.NewsCategories;

import org.apache.ibatis.annotations.Mapper;

/**
 * 新闻分类Mapper接口
 */
@Mapper
public interface NewsCategoriesMapper extends BaseMapper<NewsCategories> {
} 
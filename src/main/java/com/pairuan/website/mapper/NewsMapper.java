package com.pairuan.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pairuan.website.entity.News;

import org.apache.ibatis.annotations.Mapper;

/**
 * 新闻Mapper接口
 */
@Mapper
public interface NewsMapper extends BaseMapper<News> {
} 
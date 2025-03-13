package com.pairuan.website.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pairuan.website.entity.Carousels;

import java.util.List;

/**
 * 轮播图服务接口
 */
public interface CarouselsService extends IService<Carousels> {
    /**
     * 获取所有轮播图（包含图片URL）
     * @return 轮播图列表
     */
    List<Carousels> getAllCarouselsWithImages();
} 
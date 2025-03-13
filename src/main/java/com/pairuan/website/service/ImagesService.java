package com.pairuan.website.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pairuan.website.entity.Images;

import java.util.List;

/**
 * 图片服务接口
 */
public interface ImagesService extends IService<Images> {
    /**
     * 获取所有图片
     * @return 图片列表
     */
    List<Images> getAllImages();
} 
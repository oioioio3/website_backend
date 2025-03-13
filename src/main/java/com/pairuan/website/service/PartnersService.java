package com.pairuan.website.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pairuan.website.entity.Partners;

import java.util.List;

/**
 * 合作伙伴服务接口
 */
public interface PartnersService extends IService<Partners> {
    /**
     * 获取所有合作伙伴（包含图片URL）
     * @return 合作伙伴列表
     */
    List<Partners> getAllPartnersWithImages();
} 
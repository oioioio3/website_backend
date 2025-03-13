package com.pairuan.website.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pairuan.website.entity.Navigation;

import java.util.List;

/**
 * 导航服务接口
 */
public interface NavigationService extends IService<Navigation> {
    /**
     * 获取所有导航
     * @return 导航列表
     */
    List<Navigation> getAllNavigation();
} 
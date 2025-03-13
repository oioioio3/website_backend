package com.pairuan.website.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pairuan.website.entity.Navigation;
import com.pairuan.website.mapper.NavigationMapper;
import com.pairuan.website.service.NavigationService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 导航服务实现类
 */
@Service
public class NavigationServiceImpl extends ServiceImpl<NavigationMapper, Navigation> implements NavigationService {
    
    @Override
    public List<Navigation> getAllNavigation() {
        return list();
    }
} 
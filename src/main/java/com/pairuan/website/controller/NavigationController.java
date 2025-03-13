package com.pairuan.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pairuan.website.common.result.R;
import com.pairuan.website.entity.Navigation;
import com.pairuan.website.service.NavigationService;

import java.util.List;

/**
 * 导航接口
 */
@RestController
@RequestMapping("/api/nav")
public class NavigationController {
    
    @Autowired
    private NavigationService navigationService;
    
    /**
     * 获取所有导航
     */
    @GetMapping
    public R<List<Navigation>> getAllNavigation() {
        List<Navigation> navigationList = navigationService.getAllNavigation();
        return R.success(navigationList);
    }
} 
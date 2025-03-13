package com.pairuan.website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pairuan.website.common.result.R;
import com.pairuan.website.entity.Images;
import com.pairuan.website.service.ImagesService;


/**
 * 图片接口
 */
@RestController
@RequestMapping("/api/img")
public class ImagesController {
    
    @Autowired
    private ImagesService imagesService;
    
    /**
     * 获取所有图片
     */
    @PostMapping
    public R<List<Images>> getAllImages() {
        List<Images> imagesList = imagesService.getAllImages();
        return R.success(imagesList);
    }
} 
package com.pairuan.website.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pairuan.website.entity.Images;
import com.pairuan.website.mapper.ImagesMapper;
import com.pairuan.website.service.ImagesService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 图片服务实现类
 */
@Service
public class ImagesServiceImpl extends ServiceImpl<ImagesMapper, Images> implements ImagesService {
    
    @Override
    public List<Images> getAllImages() {
        return list();
    }
} 
package com.pairuan.website.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pairuan.website.entity.Images;
import com.pairuan.website.entity.Partners;
import com.pairuan.website.mapper.ImagesMapper;
import com.pairuan.website.mapper.PartnersMapper;
import com.pairuan.website.service.PartnersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 合作伙伴服务实现类
 */
@Service
public class PartnersServiceImpl extends ServiceImpl<PartnersMapper, Partners> implements PartnersService {
    
    @Autowired
    private ImagesMapper imagesMapper;
    
    @Override
    public List<Partners> getAllPartnersWithImages() {
        // 获取所有合作伙伴
        List<Partners> partnersList = list();
        
        // 获取所有合作伙伴涉及的图片ID
        List<Integer> imgIds = partnersList.stream()
                .map(Partners::getImgId)
                .filter(id -> id != null)
                .distinct()
                .collect(Collectors.toList());
        
        // 批量查询图片
        Map<Integer, Images> imagesMap = new HashMap<>();
        if (!imgIds.isEmpty()) {
            List<Images> images = imagesMapper.selectBatchIds(imgIds);
            imagesMap = images.stream()
                    .collect(Collectors.toMap(Images::getId, Function.identity()));
        }
        
        // 填充合作伙伴的图片URL
        Map<Integer, Images> finalImagesMap = imagesMap;
        partnersList.forEach(partner -> {
            if (partner.getImgId() != null && finalImagesMap.containsKey(partner.getImgId())) {
                partner.setImgUrl(finalImagesMap.get(partner.getImgId()).getUrl());
            }
        });
        
        return partnersList;
    }
} 
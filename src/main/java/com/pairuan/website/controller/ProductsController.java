package com.pairuan.website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pairuan.website.common.result.R;
import com.pairuan.website.entity.Carousels;
import com.pairuan.website.entity.Partners;
import com.pairuan.website.entity.Products;
import com.pairuan.website.service.CarouselsService;
import com.pairuan.website.service.PartnersService;
import com.pairuan.website.service.ProductsService;

/**
 * 产品接口
 */
@RestController
@RequestMapping("/api/products")
public class ProductsController {
    
    @Autowired
    private ProductsService productsService;
    
    @Autowired
    private CarouselsService carouselsService;
    
    @Autowired
    private PartnersService partnersService;
    
    /**
     * 获取所有产品
     */
    @GetMapping("/applications")
    public R<List<Products>> getAllProducts() {
        List<Products> productsList = productsService.getAllProductsWithImages();
        return R.success(productsList);
    }
    
    /**
     * 获取所有轮播图
     */
    @GetMapping("/carouse")
    public R<List<Carousels>> getAllCarousels() {
        List<Carousels> carouselsList = carouselsService.getAllCarouselsWithImages();
        return R.success(carouselsList);
    }
    
    /**
     * 获取所有合作伙伴
     */
    @GetMapping("/business")
    public R<List<Partners>> getAllPartners() {
        List<Partners> partnersList = partnersService.getAllPartnersWithImages();
        return R.success(partnersList);
    }
} 

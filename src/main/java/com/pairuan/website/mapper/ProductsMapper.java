package com.pairuan.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pairuan.website.entity.Products;

import org.apache.ibatis.annotations.Mapper;

/**
 * 产品Mapper接口
 */
@Mapper
public interface ProductsMapper extends BaseMapper<Products> {
} 
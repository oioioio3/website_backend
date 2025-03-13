package com.pairuan.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pairuan.website.entity.Contacts;

import org.apache.ibatis.annotations.Mapper;

/**
 * 联系我们Mapper接口
 */
@Mapper
public interface ContactsMapper extends BaseMapper<Contacts> {
} 
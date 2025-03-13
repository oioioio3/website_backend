package com.pairuan.website.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 导航实体类
 */
@Data
@TableName("navigation")
public class Navigation {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    /**
     * 导航名称
     */
    private String name;
    
    /**
     * 导航路径
     */
    private String path;
} 
package com.pairuan.website.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 轮播图实体类
 */
@Data
@TableName("carousels")
public class Carousels {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    /**
     * 图片ID
     */
    private Integer imgId;
    
    /**
     * 图片URL（非数据库字段）
     */
    @TableField(exist = false)
    private String imgUrl;
} 
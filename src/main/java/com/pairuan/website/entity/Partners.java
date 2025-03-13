package com.pairuan.website.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 合作伙伴实体类
 */
@Data
@TableName("partners")
public class Partners {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    /**
     * 合作伙伴名称
     */
    private String name;
    
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
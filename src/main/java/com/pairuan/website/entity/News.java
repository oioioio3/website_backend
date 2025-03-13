package com.pairuan.website.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 新闻实体类
 */
@Data
@TableName("news")
public class News {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 新闻标题
     */
    private String title;

    /**
     * 新闻简介
     */
    private String introduce;

    /**
     * 新闻内容
     */
    private String text;

    /**
     * 图片ID
     */
    private Integer imgId;

    /**
     * 是否置顶
     */
    private Integer isTop;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 新闻分类列表（非数据库字段）
     */
    @TableField(exist = false)
    private List<String> category;

    /**
     * 图片URL（非数据库字段）
     */
    @TableField(exist = false)
    private String imgUrl;
} 
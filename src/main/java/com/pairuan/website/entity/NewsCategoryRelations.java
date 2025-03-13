package com.pairuan.website.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 新闻分类关系实体类
 */
@Data
@TableName("news_category_relations")
public class NewsCategoryRelations {
    /**
     * 新闻ID
     */
    private Integer newsId;
    
    /**
     * 分类ID
     */
    private Integer categoryId;
} 
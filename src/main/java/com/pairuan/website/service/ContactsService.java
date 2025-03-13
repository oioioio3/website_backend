package com.pairuan.website.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pairuan.website.entity.Contacts;

/**
 * 联系我们服务接口
 */
public interface ContactsService extends IService<Contacts> {
    /**
     * 保存联系信息
     * @param contacts 联系信息
     * @return 保存后的联系信息
     */
    Contacts saveContact(Contacts contacts);
} 
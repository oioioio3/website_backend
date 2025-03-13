package com.pairuan.website.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pairuan.website.entity.Contacts;
import com.pairuan.website.mapper.ContactsMapper;
import com.pairuan.website.service.ContactsService;

import org.springframework.stereotype.Service;

/**
 * 联系我们服务实现类
 */
@Service
public class ContactsServiceImpl extends ServiceImpl<ContactsMapper, Contacts> implements ContactsService {
    
    @Override
    public Contacts saveContact(Contacts contacts) {
        save(contacts);
        return contacts;
    }
} 
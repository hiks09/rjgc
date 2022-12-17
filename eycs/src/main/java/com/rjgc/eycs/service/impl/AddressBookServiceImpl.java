package com.rjgc.eycs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.rjgc.eycs.mapper.AddressBookMapper;
import com.rjgc.eycs.service.AddressBookService;
import org.springframework.stereotype.Service;
import com.rjgc.eycs.entity.AddressBook;
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

}

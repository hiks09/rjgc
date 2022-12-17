package com.rjgc.eycs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.rjgc.eycs.entity.ShoppingCart;
import com.rjgc.eycs.mapper.ShoppingCartMapper;
import com.rjgc.eycs.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

}

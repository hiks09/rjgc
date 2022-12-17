package com.rjgc.eycs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.rjgc.eycs.entity.OrderDetail;
import com.rjgc.eycs.mapper.OrderDetailMapper;
import com.rjgc.eycs.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}
package com.rjgc.eycs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.rjgc.eycs.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {

}
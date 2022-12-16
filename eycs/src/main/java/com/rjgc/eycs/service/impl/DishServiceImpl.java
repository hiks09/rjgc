package com.rjgc.eycs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.eycs.dto.DishDto;
import com.rjgc.eycs.entity.Dish;
import com.rjgc.eycs.entity.DishFlavor;
import com.rjgc.eycs.mapper.DishMapper;
import com.rjgc.eycs.service.DishFlavorService;
import com.rjgc.eycs.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 软件工程2003陈俊文
 * 学号20201002992
 * 第周作业
 */
@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
@Autowired
    private DishFlavorService  dishFlavorService;
//新增菜品，同时保存对应的口味数据


    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        //保存菜品的基本信息到菜品表dish
        this.save(dishDto);
        Long dishId=dishDto.getId();
        //保存id
        List<DishFlavor> flavors=dishDto.getFlavors();
        flavors=flavors.stream().map((item)->{
            item.setDishId(dishId);
            return  item;
                }
        ).collect(Collectors.toList());
        //保存菜品口味数据到菜品口味表dish_flavor
        dishFlavorService.saveBatch(flavors);
    }
}

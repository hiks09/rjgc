package com.rjgc.eycs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.eycs.dto.DishDto;
import com.rjgc.eycs.entity.Dish;

/**
 * 软件工程2003陈俊文
 * 学号20201002992
 * 第周作业
 */

public interface DishService extends IService<Dish> {
    //新增菜品，同时插入菜品对应的口味数据，需要操作两张表:dish/dish_flavor
    public void saveWithFlavor(DishDto dishDto);
    //根据id查询菜品信息和对应的口味信息
    public DishDto getByIdWithFlavor(Long id);
//更新菜品信息，同时更新对应的口味信息
    public void updateWithFlavor(DishDto dishDto);
}

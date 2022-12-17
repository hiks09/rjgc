package com.rjgc.eycs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.eycs.dto.SetmealDto;
import com.rjgc.eycs.entity.Setmeal;

import java.util.List;

/**
 * 软件工程2003陈俊文
 * 学号20201002992
 * 第周作业
 */
public interface SetmealService extends IService<Setmeal> {
    //新增套餐 ，同时需要保存套餐和菜品的关联关系
    public void saveWithDish(SetmealDto setmealDto);
    //删除套餐，同时需要删除套餐和菜品的关键数据
    public void removeWithDish(List<Long> ids);
}

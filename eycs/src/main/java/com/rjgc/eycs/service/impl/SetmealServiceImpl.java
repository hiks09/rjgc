package com.rjgc.eycs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.eycs.common.CustomException;
import com.rjgc.eycs.dto.SetmealDto;
import com.rjgc.eycs.entity.Setmeal;
import com.rjgc.eycs.entity.SetmealDish;
import com.rjgc.eycs.mapper.SetmealMapper;
import com.rjgc.eycs.service.SetmealDishService;
import com.rjgc.eycs.service.SetmealService;
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
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    //新增套餐，同时需要保存套餐和菜品 的关联关系
    @Autowired
    private SetmealDishService setmealDishService;
    @Transactional
    public void saveWithDish(SetmealDto setmealDto){
        //保存套餐基本信息
        this.save(setmealDto);
        List<SetmealDish> setmealDishes=setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item)->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        //保存套餐和菜品的关联信息
        setmealDishService.saveBatch(setmealDishes);
    }

    @Override
    public void removeWithDish(List<Long> ids) {
        //查询套餐状态，确定是否可用删除
        LambdaQueryWrapper<Setmeal> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.in(Setmeal::getId,ids);
        queryWrapper.eq(Setmeal::getStatus,1);
        int count=this.count(queryWrapper);
        if(count >0){
            //如果不能删除，抛出一个业务异常
            throw new CustomException("套餐无法删除");
        }
        //如果可删，先删除套餐中的数据
        this.removeByIds(ids);
        //delete
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId,ids);
        //删除关系表
        setmealDishService.remove(lambdaQueryWrapper);

    }
}

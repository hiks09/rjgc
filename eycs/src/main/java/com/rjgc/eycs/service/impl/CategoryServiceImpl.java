package com.rjgc.eycs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.eycs.common.CustomException;
import com.rjgc.eycs.entity.Category;
import com.rjgc.eycs.entity.Dish;
import com.rjgc.eycs.entity.Setmeal;
import com.rjgc.eycs.mapper.CategoryMapper;
import com.rjgc.eycs.service.CategoryService;
import com.rjgc.eycs.service.DishService;
import com.rjgc.eycs.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 软件工程2003陈俊文
 * 学号20201002992
 * 第周作业
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>  implements CategoryService {
@Autowired
    private DishService dishService;
@Autowired
    private SetmealService setmealService;

@Override
    public void remove(Long id){
    LambdaQueryWrapper<Dish> dishLambdaQueryWrapper=new LambdaQueryWrapper<>();
    //添加查询条件，根据分类id进行查询
    dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
    int count1=dishService.count(dishLambdaQueryWrapper);
    //查询当前分类是否关联了菜品，如果已经关联，抛出一个业务异常
    if(count1>0){
     //已经关联菜品，抛出一个业务异常
throw new CustomException("当前分类下关联了菜品，不能删除");
    }
    //查询当前分类是否关联了套餐，如果关联，抛出业务异常
    //添加查询条件，根据分类id进行查询
    LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper=new LambdaQueryWrapper<>();
    setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
    int count2=setmealService.count();
    if(count2>0){
        //抛出异常
        throw new CustomException("当前分类下关联了套餐，不能删除");
    }
    super.removeById(id);
    //正常删除
}

}

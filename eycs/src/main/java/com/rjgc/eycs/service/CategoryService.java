package com.rjgc.eycs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.eycs.entity.Category;

/**
 * 软件工程2003陈俊文
 * 学号20201002992
 * 第周作业
 */

public interface CategoryService extends IService<Category> {
    void remove(Long id);
}

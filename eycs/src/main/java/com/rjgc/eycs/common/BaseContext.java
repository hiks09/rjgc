package com.rjgc.eycs.common;

/**
 * 软件工程2003陈俊文
 * 学号20201002992
 * 第周作业
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal =new ThreadLocal<>();
    //通过ThreadLocal封装工具类，用户保存和获取当前登录用户id
    //设置值
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }
    //获取值
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}

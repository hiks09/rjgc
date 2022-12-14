package com.rjgc.eycs.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 软件工程2003陈俊文
 * 学号20201002992
 * 第周作业
 */
@Configuration//分页插件-mp
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
  MybatisPlusInterceptor mybatisPlusInterceptor=new MybatisPlusInterceptor();
  mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
  return  mybatisPlusInterceptor;

}
}

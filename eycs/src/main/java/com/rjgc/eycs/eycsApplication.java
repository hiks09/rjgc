package com.rjgc.eycs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 软件工程2003陈俊文
 * 学号20201002992
 * 第周作业
 */
@Slf4j//日志注解
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
public class eycsApplication {
     public static void main(String[] args){
         SpringApplication.run(eycsApplication.class,args);
         log.info("鹅鸭超市开饭了...");
     }
}

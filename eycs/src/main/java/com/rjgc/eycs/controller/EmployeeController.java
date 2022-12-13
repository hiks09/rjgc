package com.rjgc.eycs.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rjgc.eycs.common.R;
import com.rjgc.eycs.entity.Employee;
import com.rjgc.eycs.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 软件工程2003陈俊文
 * 学号20201002992
 * 第周作业
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        //1将页面提交的密码进行md5加密处理
        String password=employee.getPassword();
        password= DigestUtils.md5DigestAsHex(password.getBytes());

        //2根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp=employeeService.getOne(queryWrapper);

        //3如果没有查询则返回登录失败结果
        if(emp==null){
            return R.error("没有查询到此用户名");
        }

        //4密码比对，如果不一致则返回登录失败结果
        if(!emp.getPassword().equals(password)){
            return R.error("密码错误，登录失败");
        }

        //5查看员工状态，禁用不能登录
        if(emp.getStatus()==0){
            return R.error("账号已经禁用");
        }

        //6登录成功，将员工id存入session并返回登录结果
        request.getSession().setAttribute("employee" , emp.getId());
        return R.success(emp);

    }
}

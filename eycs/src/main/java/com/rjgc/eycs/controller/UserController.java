package com.rjgc.eycs.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rjgc.eycs.common.R;
import com.rjgc.eycs.entity.User;
import com.rjgc.eycs.service.UserService;
import com.rjgc.eycs.utils.SMSUtils;
import com.rjgc.eycs.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 软件工程2003陈俊文
 * 学号20201002992
 * 第周作业
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        //获取手机号
        String phone=user.getPhone();
        if(StringUtils.isNotEmpty(phone)){
            //生成随机的4位验证码
            String code= ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code={}",code);
            //调用阿里云短信服务
            SMSUtils.sendMessage("鹅鸭超市","SMS_264900311",phone,code);
            //生成验证码存到session
            session.setAttribute(phone,code);
            return  R.success("手机验证码短信发送成功");
        }
        session.setAttribute("user",user.getId());
        return R.error("短信发送失败");
    }

    @PostMapping("/login")
    public R<User> login (@RequestBody Map map, HttpSession session){
        //获取手机号
        String phone=map.get("phone").toString();
        //获取验证码
        String code=map.get("code").toString();
        //从session中获取保存的验证码
        Object codeInSession=session.getAttribute(phone);
        //进行比对
        if(codeInSession!=null&&codeInSession.equals(code)){

            LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,phone);
            User user=userService.getOne(queryWrapper);
            if(user==null){
                user=new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user",user.getId());
            return  R.success(user);
        }
        return  R.error("登录失败");
    }
}

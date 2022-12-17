package com.rjgc.eycs.filter;

import com.alibaba.fastjson.JSON;
import com.rjgc.eycs.common.BaseContext;
import com.rjgc.eycs.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebFault;
import java.io.IOException;

/**
 * 软件工程2003陈俊文
 * 学号20201002992
 * 第周作业
 */


@WebFilter(filterName="loginCheckFilter",urlPatterns="/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static  final AntPathMatcher PATH_MATCHER=new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest)  servletRequest;
        HttpServletResponse response=(HttpServletResponse)  servletResponse;



        //定义不需要处理的请求路径
        String[] urls=new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login"
        };
        //1、获取本次请求的URI
        String requestURI=request.getRequestURI();          //backend/index.html
        log.info("拦截到请求:{}",requestURI);

        //2、判断本次请求是否需要处理
        boolean check=check(urls,requestURI);

       //3、如果不需要处理，则直接放行
        if(check){
            log.info("本次请求{}不需要处理",requestURI);
        filterChain.doFilter(request,response);
        return;
        }

        //4、判断登录状态，如果已登录，则直接放行
        if(request.getSession().getAttribute("employee")!=null){
            log.info("用户已登录,用户 id为：{}",request.getSession().getAttribute("employee"));
            Long empId=(Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            filterChain.doFilter(request,response);
            return;
        }
        //4、判断登录状态，如果已登录，则直接放行
        if(request.getSession().getAttribute("user")!=null){
            log.info("用户已登录,用户 id为：{}",request.getSession().getAttribute("user"));
            Long userId=(Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);
            filterChain.doFilter(request,response);
            return;
        }
        log.info("用户未登录");
        //5、如果未登录则返回未登录结果，通过输出流方式向客户端响应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        log.info("拦截到请求:{}",request.getRequestURI());
        filterChain.doFilter(request,response);
    }


    public boolean check(String[]urls,String requestURI){
        for(String url:urls){
            boolean match=PATH_MATCHER.match(url,requestURI);
            if(match) {
                return true;
            }
        }
        return false;
    }
}

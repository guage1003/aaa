package com.jinguizi.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: uploading
 * @description:
 * @Author: liuzhiwen
 * @Create: 2021-01-15 16:55
 **/
//@WebFilter
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            //判断请求路径是否为登录路径，如果是就放行
            if (request.getRequestURI().contains("login")) {
                //放行
                filterChain.doFilter(request,response);
            }
            String rights = CookieUtils.getCookieValue(request, "rights");
            System.out.println(rights);
            List<String> list = JsonUtils.jsonToList(rights,null);
            String uri = request.getRequestURI();
            if (list.contains(uri)){
                filterChain.doFilter(request,response);
            }else {
                //输出响应流
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code","4005");
                jsonObject.put("msg", "无权访问");
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                response.getOutputStream().write(jsonObject.toString().getBytes(StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code","4005");
            jsonObject.put("msg", "无权访问");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getOutputStream().write(jsonObject.toString().getBytes(StandardCharsets.UTF_8));
        }
    }
}

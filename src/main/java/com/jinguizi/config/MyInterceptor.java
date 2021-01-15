package com.jinguizi.config;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@Component
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        //如果路劲中包含login，直接放行
        if(requestURI.contains("login")){
            return true;
        }
        // 1.获取token
        String token = getToken(request);
        if (StringUtils.isBlank(token)) {
            // 2.从headers中获取
            token = request.getHeader("token");
        }
        if (StringUtils.isBlank(token)) {
            // 3.从请求参数获取
            token = request.getParameter("token");
        }
        if (StringUtils.isBlank(token)) {
            //输出响应流
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg", "403");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getOutputStream().write(jsonObject.toString().getBytes(StandardCharsets.UTF_8));
            return false;
        }
        // 验证token
        CheckResult checkResult = JwtUtil.validateJWT(token);
        if (checkResult.isSuccess()) {
            // 验证通过
            return true;
        } else {
            if (checkResult.getErrCode().equals(SystemConstant.JWT_ERRCODE_EXPIRE)) {
                //输出响应流
                JSONObject jsonObject = new JSONObject();
                //jsonObject.put("msg", SystemConstant.JWT_ERRCODE_EXPIRE);
                ResultCode errorToken = ResultCode.ERROR_TOKEN;
                jsonObject.put("code",errorToken.code());
                jsonObject.put("msg",errorToken.message());
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                response.getOutputStream().write(jsonObject.toString().getBytes(StandardCharsets.UTF_8));
                return false;
            } else if (checkResult.getErrCode().equals(SystemConstant.JWT_ERRCODE_FAIL)) {
                //输出响应流
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("msg", SystemConstant.JWT_ERRCODE_FAIL);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                response.getOutputStream().write(jsonObject.toString().getBytes(StandardCharsets.UTF_8));
                return false;
            }
            //输出响应流
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg", "403");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getOutputStream().write(jsonObject.toString().getBytes(StandardCharsets.UTF_8));
            return false;
        }
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        JwtUtil jwtUtil = new JwtUtil();
        CheckResult checkResult = jwtUtil.validateJWT(token);
        if(checkResult.isSuccess()){
            return token;
        }else{
            checkResult.setErrCode(SystemConstant.JWT_ERRCODE_EXPIRE);
            return  null;
        }
    }
}
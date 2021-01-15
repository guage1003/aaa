package com.jinguizi.config;


import com.jinguizi.bean.SysLog;
import com.jinguizi.bean.User;
import com.jinguizi.mapper.SysLogMapper;
import com.jinguizi.mapper.UserMapper;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Aspect
@Component
public class LogAspect {
    @Resource
    private UserMapper userMapper;

    @Resource
    private SysLogMapper sysLogDao;

    //private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @Pointcut("@annotation(com.jinguizi.config.Log)")
    public void pointcut() { }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws IOException {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveLog(point, time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time) throws IOException {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = new SysLog();
        Log logAnnotation = method.getAnnotation(Log.class);
        if (logAnnotation != null) {
            String fileName = (String) request.getSession().getAttribute("fileName");
            // 注解上的描述
            sysLog.setOperation(logAnnotation.value()+"---"+fileName);
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            String params = "";
            for (int i = 0; i < args.length; i++) {
                params += "  " + paramNames[i] + ": " + args[i];
            }
            sysLog.setParams(params);
        }
        // 获取request

        // 设置IP地址
        sysLog.setIp(IpUtils.getIpAddr(request));
        // 模拟一个用户名

        Claims claims=null;
        User user=null;
        String token="";
        if(request.getRequestURI().contains("login")){
            Integer id = (Integer) request.getSession().getAttribute("id");
            user = userMapper.findUserById(id);
            System.out.println(id);
        }else{
            token = request.getHeader("token");
            claims = JwtUtil.parseJWT(token);
            user = userMapper.findUserById(Integer.parseInt(claims.getId()));
        }
        if(user!=null){
            sysLog.setUsername(user.getUsername());
        }

        sysLog.setCreateTime(new Date());
        // 保存系统日志
        sysLogDao.saveSysLog(sysLog);
    }


}

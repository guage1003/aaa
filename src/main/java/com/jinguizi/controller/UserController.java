package com.jinguizi.controller;

import com.jinguizi.bean.User;
import com.jinguizi.config.JwtUtil;
import com.jinguizi.config.Log;
import com.jinguizi.config.Result;
import com.jinguizi.config.ResultCode;
import com.jinguizi.service.AuthService;
import com.jinguizi.service.UserService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Title: UserController
 * @Description:用户登录页面
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2020/12/23  17:13
 */
//@CrossOrigin
@RestController
@RequestMapping("jinguizi/user")
public class UserController {
    @Resource
    UserService userService;

    @Resource
    AuthService authService;

    @Resource
    JwtUtil jwtUtil;

    private static final long EXPIRE_TIME=60*24*60*1000;

    /**
     * 登录
     * @param user 包含用户姓名以及密码
     * @return Result 如果用户不存在通过Result返回失败，否则返回成功
     */
    @Log("登录了")
    @PostMapping("login")
    public Result findUserByNameAndPassword(@RequestBody User user, HttpSession session){

        //判断user表中是否存在该用户
        User user2 = userService.findUserByNameAndPassword(user);
        //不存在就返回失败
        if(user2==null){
            return Result.failure(ResultCode.FAIL);
        }
        try {
            //存在的话，就通过jwtUtil创建一个token，返回给前端
            String token = jwtUtil.createJWT(user2.getId() + "", "user", Long.parseLong(EXPIRE_TIME+""));
            session.setAttribute("id",user2.getId());
            Map<String, Object> map = new HashMap<>();
            map.put("token",token);
            map.put("userId",user2.getId());
            List<Map<String, Object>> authorityList = authService.loadMenu(user2.getId());
            map.put("authorityList",authorityList);
            return Result.success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(ResultCode.FAIL);
        }
    }

   /* @PostMapping("verifyUserByName")
    public Result verifyUserByName(String userName){
        User user=userService.verifyUserByName(userName);
        if(user!=null){
            return Result.error("用户名已存在");
        }

        return Result.ok();
    }

    @PostMapping("registerUser")
    public Result registerUser(User user){
        try {
            userService.registerUser(user);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("注册失败");
        }
    }*/

}

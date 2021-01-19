package com.jinguizi.service;

import com.jinguizi.bean.User;
import com.jinguizi.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: UserService
 * @Description:
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2020/12/23  17:16
 */
@Service
@Transactional
public class UserService {
    @Resource
    UserMapper userMapper;

    public User findUserByNameAndPassword(User user){
        return userMapper.findUserByNameAndPassword(user);
    }

    public User verifyUserByName(String username){
        return userMapper.verifyUserByName(username);
    }

    public void registerUser(User user){
        userMapper.registerUser(user);
    }

    public List<User> findAllUser(){
        List<User> userList = userMapper.findAllUser();
        return userList;
    }
}

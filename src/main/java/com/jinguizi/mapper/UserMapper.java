package com.jinguizi.mapper;

import com.jinguizi.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.net.InetAddress;

/**
 * @Title: UserMapper
 * @Description:
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2020/12/23  17:16
 */
@Mapper
public interface UserMapper {
    User findUserByNameAndPassword(User user);

    User verifyUserByName(String username);

    void registerUser(User user);

    User findUserById(Integer id);
}

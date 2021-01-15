package com.jinguizi.bean;

import java.io.Serializable;

/**
 * @Title: User
 * @Description:
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2020/12/23  17:15
 */
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private Integer userright;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userright=" + userright +
                '}';
    }

    public Integer getUserright() {
        return userright;
    }

    public void setUserright(Integer userright) {
        this.userright = userright;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

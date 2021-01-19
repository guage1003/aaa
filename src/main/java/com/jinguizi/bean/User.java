package com.jinguizi.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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
    private List<Role> roleList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(userright, user.userright) &&
                Objects.equals(roleList, user.roleList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, userright, roleList);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userright=" + userright +
                ", roleList=" + roleList +
                '}';
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
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

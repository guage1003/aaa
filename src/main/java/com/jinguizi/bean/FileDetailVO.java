package com.jinguizi.bean;

/**
 * @Title: FileDetailVO
 * @Description:
 * @Auther: hancoong
 * @Version: 1.0
 * @create 2020/12/24  9:15
 */
public class FileDetailVO extends FileDetail{
    private String username;

    @Override
    public String toString() {
        return "FileDetailVO{" +
                "username='" + username + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

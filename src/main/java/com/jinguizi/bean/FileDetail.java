package com.jinguizi.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.net.Inet4Address;
import java.util.Date;

/**
 * @Title: FileDetail
 * @Description:
 * @Auther: hancoong
 * @Version: 1.0
 * @create 2020/12/23  18:17
 */
public class FileDetail implements Serializable {
    private Integer id;
    private String fileName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;
    private Integer count;
    private Integer userId;

    @Override
    public String toString() {
        return "FileDetail{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", uploadTime=" + uploadTime +
                ", count=" + count +
                ", userId=" + userId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

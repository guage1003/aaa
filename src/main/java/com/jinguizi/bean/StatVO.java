package com.jinguizi.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Title: StatVO
 * @Description:
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2020/12/23  14:18
 */
public class StatVO {
    private Integer count;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTime;
    private String uploadTime;

    @Override
    public String toString() {
        return "StatVO{" +
                "count=" + count +
                ", dateTime=" + dateTime +
                ", uploadTime='" + uploadTime + '\'' +
                '}';
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}

package com.jinguizi.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * @Title: PageResult
 * @Description:
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2020/12/25  10:33
 */
public class PageResult implements Serializable {

    private Integer pageNum;
    private Integer pageSize;
    private String modelName;
    private Double maxNum;
    private Double minNum;
    private String channel;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    @Override
    public String toString() {
        return "PageResult{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", modelName='" + modelName + '\'' +
                ", maxNum=" + maxNum +
                ", minNum=" + minNum +
                ", channel='" + channel + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                '}';
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Double getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Double maxNum) {
        this.maxNum = maxNum;
    }

    public Double getMinNum() {
        return minNum;
    }

    public void setMinNum(Double minNum) {
        this.minNum = minNum;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}

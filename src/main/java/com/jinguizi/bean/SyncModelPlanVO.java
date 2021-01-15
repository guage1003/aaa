package com.jinguizi.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Title: SyncModelPlanVO
 * @Description:
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2021/1/7  14:44
 */
public class SyncModelPlanVO extends SyncModelPlan {
    private String userName;

   // @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String maxTime;
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private String minTime;

    @Override
    public String toString() {
        return "SyncModelPlanVO{" +
                "userName='" + userName + '\'' +
                ", maxTime=" + maxTime +
                ", minTime=" + minTime +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(String maxTime) {
        this.maxTime = maxTime;
    }

    public String getMinTime() {
        return minTime;
    }

    public void setMinTime(String minTime) {
        this.minTime = minTime;
    }
}

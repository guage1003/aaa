package com.jinguizi.bean;

import java.io.Serializable;

/**
 * @Title: SearchHostParams
 * @Description:
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2021/1/13  14:48
 */
public class SearchHostParams implements Serializable {
    private Integer pageNum;
    private Integer pageSize;
    private String modelId;
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }
}

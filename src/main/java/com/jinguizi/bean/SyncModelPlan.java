package com.jinguizi.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: SyncModelPlan
 * @Description:
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2021/1/7  14:20
 */
public class SyncModelPlan implements Serializable {
    private String modelId;
    private String name;
    private String productName;
    private String cp;
    private String productType;
    private String productUrl;
    private Integer type;
    private Integer paidGrade;
    private String customModel;
    private String scriptId;
    private String scriptName;
    private String scriptContent;
    private String label;
    private Integer gid;
    private String createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
    private String userId;

    @Override
    public String toString() {
        return "SyncModelPlan{" +
                "modelId='" + modelId + '\'' +
                ", name='" + name + '\'' +
                ", productName='" + productName + '\'' +
                ", cp='" + cp + '\'' +
                ", productType='" + productType + '\'' +
                ", productUrl='" + productUrl + '\'' +
                ", type=" + type +
                ", paidGrade=" + paidGrade +
                ", customModel='" + customModel + '\'' +
                ", scriptId='" + scriptId + '\'' +
                ", scriptName='" + scriptName + '\'' +
                ", scriptContent='" + scriptContent + '\'' +
                ", label='" + label + '\'' +
                ", gid=" + gid +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", userId='" + userId + '\'' +
                '}';
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPaidGrade() {
        return paidGrade;
    }

    public void setPaidGrade(Integer paidGrade) {
        this.paidGrade = paidGrade;
    }

    public String getCustomModel() {
        return customModel;
    }

    public void setCustomModel(String customModel) {
        this.customModel = customModel;
    }

    public String getScriptId() {
        return scriptId;
    }

    public void setScriptId(String scriptId) {
        this.scriptId = scriptId;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getScriptContent() {
        return scriptContent;
    }

    public void setScriptContent(String scriptContent) {
        this.scriptContent = scriptContent;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

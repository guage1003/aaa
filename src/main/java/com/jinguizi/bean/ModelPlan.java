package com.jinguizi.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: ModelPlan
 * @Description:
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2020/12/23  15:09
 */
public class ModelPlan implements Serializable {
    private Integer id;
    private String fileName;
    private String modelName;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date pushTime;
    private Double outboundCost;
    private Double smsCost;
    private String income;
    private Integer registerCount;
    private String channel;
    private Integer triggerCount;
    private String operatorsLine;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date groupTime;
    private Integer userId;
    private Integer sendSuccessCount;
    private Integer dataCount;
    private Integer connectCount;
    private Integer fileId;
    private String dataType;


    @Override
    public String toString() {
        return "ModelPlan{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", pushTime=" + pushTime +
                ", outboundCost=" + outboundCost +
                ", smsCost=" + smsCost +
                ", income=" + income +
                ", registerCount=" + registerCount +
                ", channel='" + channel + '\'' +
                ", triggerCount=" + triggerCount +
                ", operatorsLine='" + operatorsLine + '\'' +
                ", groupTime=" + groupTime +
                ", userId=" + userId +
                ", sendSuccessCount=" + sendSuccessCount +
                ", dataCount=" + dataCount +
                ", connectCount=" + connectCount +
                ", fileId=" + fileId +
                ", dataType='" + dataType + '\'' +
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

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public Double getOutboundCost() {
        return outboundCost;
    }

    public void setOutboundCost(Double outboundCost) {
        this.outboundCost = outboundCost;
    }

    public Double getSmsCost() {
        return smsCost;
    }

    public void setSmsCost(Double smsCost) {
        this.smsCost = smsCost;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public Integer getRegisterCount() {
        return registerCount;
    }

    public void setRegisterCount(Integer registerCount) {
        this.registerCount = registerCount;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getTriggerCount() {
        return triggerCount;
    }

    public void setTriggerCount(Integer triggerCount) {
        this.triggerCount = triggerCount;
    }

    public String getOperatorsLine() {
        return operatorsLine;
    }

    public void setOperatorsLine(String operatorsLine) {
        this.operatorsLine = operatorsLine;
    }

    public Date getGroupTime() {
        return groupTime;
    }

    public void setGroupTime(Date groupTime) {
        this.groupTime = groupTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSendSuccessCount() {
        return sendSuccessCount;
    }

    public void setSendSuccessCount(Integer sendSuccessCount) {
        this.sendSuccessCount = sendSuccessCount;
    }

    public Integer getDataCount() {
        return dataCount;
    }

    public void setDataCount(Integer dataCount) {
        this.dataCount = dataCount;
    }

    public Integer getConnectCount() {
        return connectCount;
    }

    public void setConnectCount(Integer connectCount) {
        this.connectCount = connectCount;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}

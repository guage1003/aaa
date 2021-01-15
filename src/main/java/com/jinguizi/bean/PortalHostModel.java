package com.jinguizi.bean;


import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

public class PortalHostModel {

    @Excel(name = "id")
    private int id;
    @Excel(name = "mode_id")
    private String modeId;
    @Excel(name = "user_id")
    private int userId;
    @Excel(name = "host_type")
    private String hostType;
    @Excel(name = "url")
    private String url;
    @Excel(name = "host")
    private String host;
    @Excel(name = "path")
    private String path;
    @Excel(name = "update_date",exportFormat = "yyyy-MM-dd")
    private Date updateDate;
    @Excel(name = "name")
    private String name;
    @Excel(name = "source_name")
    private String sourceName;
    @Excel(name = "source_type")
    private String sourceType;
    @Excel(name = "link_describe")
    private String linkDescribe;
    @Excel(name = "link_content")
    private String linkContent;
    @Excel(name = "class")
    private String hostClass;
    @Excel(name = "subclass_1")
    private String subclass1;
    @Excel(name = "subclass_2")
    private String subclass2;
    @Excel(name = "subclass_3")
    private String subclass3;
    @Excel(name = "subclass_4")
    private String subclass4;
    @Excel(name = "subclass_5")
    private String subclass5;
    @Excel(name = "state")
    private int state;

    //spring boot 自动注入的时候使用的是无参构造函数，重载构造函数必须添加默认
    public PortalHostModel()
    {

    }

    public PortalHostModel(int id, String modeId, int userId, String hostType, String url, String host, String path, Date updateDate, String name, String sourceName, String sourceType, String linkDescribe, String linkContent, String hostClass, String subclass1, String subclass2, String subclass3, String subclass4, String subclass5, int state)
    {
        this.id = id;
        this.modeId = modeId;
        this.userId = userId;
        this.hostType = hostType;
        this.url = url;
        this.host = host;
        this.path = path;
        this.updateDate = updateDate;
        this.name = name;
        this.sourceName = sourceName;
        this.sourceType = sourceType;
        this.linkDescribe = linkDescribe;
        this.linkContent = linkContent;
        this.hostClass = hostClass;
        this.subclass1 = subclass1;
        this.subclass2 = subclass2;
        this.subclass3 = subclass3;
        this.subclass4 = subclass4;
        this.subclass5 = subclass5;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setModeId(String modeId) {
        this.modeId = modeId;
    }

    public void setHostType(String hostType) {
        this.hostType = hostType;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public void setLinkDescribe(String linkDescribe) {
        this.linkDescribe = linkDescribe;
    }

    public void setLinkContent(String linkContent) {
        this.linkContent = linkContent;
    }

    public void setHostClass(String hostClass) {
        this.hostClass = hostClass;
    }

    public void setSubclass1(String subclass1) {
        this.subclass1 = subclass1;
    }

    public void setSubclass2(String subclass2) {
        this.subclass2 = subclass2;
    }

    public void setSubclass3(String subclass3) {
        this.subclass3 = subclass3;
    }

    public void setSubclass4(String subclass4) {
        this.subclass4 = subclass4;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getModeId() {
        return modeId;
    }

    public String getHostType() {
        return hostType;
    }

    public String getUrl() {
        return url;
    }

    public String getHost() {
        return host;
    }

    public String getPath() {
        return path;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public String getName() {
        return name;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getSourceType() {
        return sourceType;
    }

    public String getLinkDescribe() {
        return linkDescribe;
    }

    public String getLinkContent() {
        return linkContent;
    }

    public String getHostClass() {
        return hostClass;
    }

    public String getSubclass1() {
        return subclass1;
    }

    public String getSubclass2() {
        return subclass2;
    }

    public String getSubclass3() {
        return subclass3;
    }

    public String getSubclass4() {
        return subclass4;
    }

    public int getState() {
        return state;
    }

    public void setSubclass5(String subclass5) {
        this.subclass5 = subclass5;
    }

    public String getSubclass5() {
        return subclass5;
    }
}

package com.jinguizi.bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Title: uploading
 * @description:
 * @Author: liuzhiwen
 * @Create: 2021-01-15 14:28
 **/
public class Authority implements Serializable {
    private Integer id;
    private String path;
    private String name;
    private String component;
    private Integer type;
    private Integer parentId;
    private Integer order;
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", component='" + component + '\'' +
                ", type=" + type +
                ", parentId=" + parentId +
                ", order=" + order +
                ", icon='" + icon + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return Objects.equals(id, authority.id) &&
                Objects.equals(path, authority.path) &&
                Objects.equals(name, authority.name) &&
                Objects.equals(component, authority.component) &&
                Objects.equals(type, authority.type) &&
                Objects.equals(parentId, authority.parentId) &&
                Objects.equals(order, authority.order) &&
                Objects.equals(icon, authority.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path, name, component, type, parentId, order, icon);
    }
}

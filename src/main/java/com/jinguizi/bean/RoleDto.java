package com.jinguizi.bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Title: uploading
 * @description:
 * @Author: liuzhiwen
 * @Create: 2021-01-18 16:22
 **/
public class RoleDto implements Serializable {
    private Integer id;

    @Override
    public String toString() {
        return "RoleDto{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto roleDto = (RoleDto) o;
        return Objects.equals(id, roleDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

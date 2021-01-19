package com.jinguizi.bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Title: uploading
 * @description:
 * @Author: liuzhiwen
 * @Create: 2021-01-18 16:20
 **/
public class AuthorityDto implements Serializable {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorityDto that = (AuthorityDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AuthorityDto{" +
                "id=" + id +
                '}';
    }
}

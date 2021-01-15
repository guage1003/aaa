package com.jinguizi.bean;

import java.io.Serializable;

/**
 * @Title: AuditHostParams
 * @Description:
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2021/1/13  16:45
 */
public class AuditHostParams implements Serializable {
    private Integer id;
    private String modeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModeId() {
        return modeId;
    }

    public void setModeId(String modeId) {
        this.modeId = modeId;
    }
}

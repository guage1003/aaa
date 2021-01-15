package com.jinguizi.bean;

import java.io.Serializable;

/**
 * @Title: DeleteFileByIdParams
 * @Description:
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2021/1/13  10:58
 */
public class DeleteFileByIdParams implements Serializable {
    private Integer fileId;

    @Override
    public String toString() {
        return "DeleteFileByIdParams{" +
                "fileId=" + fileId +
                '}';
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
}

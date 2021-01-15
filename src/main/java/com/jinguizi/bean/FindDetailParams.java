package com.jinguizi.bean;

/**
 * @Title: FindDetailParams
 * @Description:
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2021/1/13  10:56
 */
public class FindDetailParams {
    private Integer pageNum;
    private Integer pageSize;
    private String fileName;

    @Override
    public String toString() {
        return "findFileDetailParams{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", fileName='" + fileName + '\'' +
                '}';
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

package com.jinguizi.config;

public enum ResultCode {
    // 自定义状态码
    SUCCESS(2001, "成功"),

    FAIL(4001, "失败"),

    ERROR_STAT(2002, "统计失败"),

    ERROR_UPLOAD(4002, "上传失败"),

    ERROR_MODELNAME(4003, "设备名重复"),

    ERROR_UPLOAD_EMPTY(4004,"文件为空"),

    NO_PERMISSION(4005,"无权使用"),

    ERROR_TOKEN(5001, "token失效");

    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public static String getMessage(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return name;
    }

    public static Integer getCode(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }
}
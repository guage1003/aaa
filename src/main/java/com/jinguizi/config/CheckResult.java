package com.jinguizi.config;

import io.jsonwebtoken.Claims;


public class CheckResult {
    private boolean success;
    private Claims claims;
    private String errCode;

    @Override
    public String toString() {
        return "CheckResult{" +
                "success=" + success +
                ", claims=" + claims +
                ", errCode='" + errCode + '\'' +
                '}';
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}
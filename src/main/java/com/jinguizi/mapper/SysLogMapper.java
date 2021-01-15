package com.jinguizi.mapper;

import com.jinguizi.bean.SysLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysLogMapper {
    void saveSysLog(SysLog syslog);
}
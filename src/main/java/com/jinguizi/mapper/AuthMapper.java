package com.jinguizi.mapper;

import com.jinguizi.bean.Authority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuthMapper {
    public List<Authority> findAuthorityByParentId(@Param("parentId") Integer parentId ,@Param("id") Integer id);
}

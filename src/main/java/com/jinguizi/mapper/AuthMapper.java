package com.jinguizi.mapper;

import com.jinguizi.bean.Authority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuthMapper {
    public List<Authority> findAuthorityByParentIdAndUserId(@Param("parentId") Integer parentId , @Param("id") Integer id);

    void addAuthority(Authority authority);

    List<Authority> findAuthorityByParentId(Integer ParentId);

    void updateAuthority(Authority authority);

    void deleteAuthority(Integer id);

    List<Authority> findAuthorityByRoleId(Integer RoleId);
}

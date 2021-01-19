package com.jinguizi.mapper;

import com.jinguizi.bean.Authority;
import com.jinguizi.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {

    List<Role> findAllRole();

    void addRole(Role role);

    void addRoleIdAndAuthorityId(@Param("roleId") Integer roleId,@Param("list") List<Authority> List);

    void deleteRoleIdAndAuthorityIdByRoleId(Integer roleId);

    void updateRole(Role role);

    void deleteRole(Integer id);

    List<Role> findRoleByUserId(Integer userId);
}

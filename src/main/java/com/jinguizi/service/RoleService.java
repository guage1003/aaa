package com.jinguizi.service;

import com.jinguizi.bean.Role;
import com.jinguizi.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: uploading
 * @description:
 * @Author: liuzhiwen
 * @Create: 2021-01-18 11:20
 **/
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public List<Role> findAllRole() {
        return roleMapper.findAllRole();
    }

    public void addRole(Role role) {
        //添加角色表
        roleMapper.addRole(role);
        //添加角色和权限中间表
        roleMapper.addRoleIdAndAuthorityId(role.getId(),role.getAuthorityList());
    }

    public void updateRole(Role role) {
        //将角色与权限的中间表删除
        roleMapper.deleteRoleIdAndAuthorityIdByRoleId(role.getId());
        //修改角色表
        roleMapper.updateRole(role);
        //判断是否有权限
        if (role.getAuthorityList()!=null&&role.getAuthorityList().size()>0){
            //添加角色和权限的关系
            roleMapper.addRoleIdAndAuthorityId(role.getId(),role.getAuthorityList());
        }
    }

    public void deleteRole(Integer id) {
        roleMapper.deleteRoleIdAndAuthorityIdByRoleId(id);
        roleMapper.deleteRole(id);
    }
}

package com.jinguizi.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.jinguizi.bean.Role;
import com.jinguizi.bean.RoleDto;
import com.jinguizi.config.Result;
import com.jinguizi.config.ResultCode;
import com.jinguizi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Title: uploading
 * @description:
 * @Author: liuzhiwen
 * @Create: 2021-01-18 11:13
 **/
@RestController
@RequestMapping("jinguizi/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查找所有的角色，以及角色对应的权限
     * @return 将查询出来的角色，通过Result工具类返回
     */
    @PostMapping("findAllRole")
    public Result findAllRole(){
        try {
            List<Role> roleList = roleService.findAllRole();
            return Result.success(roleList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(ResultCode.FAIL);
        }
    }

    /**
     * 添加角色，绑定角色与权限的关系
     * @param role 角色实体类，包含角色名称和角色需要添加的权限
     * @return 将添加的结果返回
     */
    @PostMapping("addRole")
    public Result addRole(@RequestBody Role role){
        try {
            if (role==null){
                return Result.failure(ResultCode.ERROR_PARAMETER);
            }
            roleService.addRole(role);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(ResultCode.FAIL);
        }
    }

    /**
     * 修改角色，以及角色的权限
     * @param role 角色实体类，包含角色名称和角色需要添加的权限
     * @return 返回修改的结果
     */
    @PostMapping("updateRole")
    public Result updateRole(@RequestBody Role role){
        try {
            if (role == null||role.getId()==null){
                return Result.failure(ResultCode.ERROR_PARAMETER);
            }
            roleService.updateRole(role);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(ResultCode.FAIL);
        }
    }

    /**
     * 根据id删除角色
     * @param roleDto 角色id
     * @return 返回删除角色的结果
     */
    @PostMapping("deleteRole")
    public Result deleteRole(@RequestBody RoleDto roleDto){
        try {
            if (roleDto.getId()==null||roleDto.getId()<0){
                return Result.failure(ResultCode.ERROR_PARAMETER);
            }
            roleService.deleteRole(roleDto.getId());
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(ResultCode.FAIL);
        }
    }
}

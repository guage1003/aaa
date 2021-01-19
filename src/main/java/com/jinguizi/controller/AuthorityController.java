package com.jinguizi.controller;

import com.jinguizi.bean.Authority;
import com.jinguizi.bean.AuthorityDto;
import com.jinguizi.config.Result;
import com.jinguizi.config.ResultCode;
import com.jinguizi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Title: uploading
 * @description:
 * @Author: liuzhiwen
 * @Create: 2021-01-18 09:30
 **/
@RestController
@RequestMapping("jinguizi/authority")
public class AuthorityController {

    @Autowired
    private AuthService authService;

    /**
     * 添加权限
     * @param authority 权限实体类
     * @return  将添加的结果返回。
     */
    @PostMapping("addAuthority")
    public Result addAuthority(@RequestBody Authority authority){
        try {
            //判断参数
            if (authority==null){
                return Result.failure(ResultCode.ERROR_PARAMETER);
            }
            authService.addAuthority(authority);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(ResultCode.FAIL);
        }
    }

    /**
     * 查询所有的权限
     * @return 将查询出来的权限集合，通过Result工具类返回
     */
    @PostMapping("findAllAuthority")
    public Result findAllAuthority(){
        try {
            List<Map<String, Object>> authorityList = authService.findAllAuthority();
            return Result.success(authorityList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(ResultCode.FAIL);
        }
    }

    /**
     * 修改权限
     * @param authority 权限实体类
     * @return 将修改的结果进行返回
     */
    @PostMapping("updateAuthority")
    public Result updateAuthority(@RequestBody Authority authority){
        try {
            //参数为空或者权限id为空，返回参数错误的提示信息
            if (authority==null||authority.getId()==null){
                return Result.failure(ResultCode.ERROR_PARAMETER);
            }
            authService.updateAuthority(authority);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(ResultCode.FAIL);
        }
    }

    /**
     * 根据id删除权限
     * @param authorityDto 需要删除的权限的id
     * @return 将删除的结果进行返回
     */
    @PostMapping("deleteAuthority")
    public Result deleteAuthority(@RequestBody AuthorityDto authorityDto){
        try {
            if (authorityDto.getId() == null){
                return Result.failure(ResultCode.ERROR_PARAMETER);
            }
            authService.deleteAuthority(authorityDto.getId());
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(ResultCode.FAIL);
        }
    }

}

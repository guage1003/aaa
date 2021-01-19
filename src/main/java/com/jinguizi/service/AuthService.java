package com.jinguizi.service;

import com.jinguizi.bean.Authority;
import com.jinguizi.mapper.AuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: AuthService
 * @description:
 * @Author: liuzhiwen
 * @Create: 2021-01-15 14:18
 **/
@Service
public class AuthService {

    @Autowired
    private AuthMapper authmapper;

    public List<Map<String,Object>> loadMenu(Integer id){
        //根据用户id查出用户所拥有的一级目录
        List<Authority> list = authmapper.findAuthorityByParentIdAndUserId(0,id);
        //根据一级目录和用户id查出所拥有的权限
        List<Map<String, Object>> resultList = findAuthorityByUserId(list,id);
        return resultList;
    }

    public List<Map<String, Object>> findAuthorityByUserId(List<Authority> list, Integer id){
        List<Map<String, Object>> maps = new ArrayList<>();
        //遍历目录
        for (Authority authority : list) {
            HashMap<String, Object> map = new HashMap<>();
            //子目录或权限
            List<Map<String, Object>> children = new ArrayList<>();
            //进行封装
            map.put("id",authority.getId());
            map.put("component",authority.getComponent());
            map.put("name",authority.getName());
            map.put("path",authority.getPath());
            map.put("type",authority.getType());
            map.put("parentId",authority.getParentId());
            map.put("order",authority.getOrder());
            map.put("icon",authority.getIcon());
            List<Authority> authorityList = authmapper.findAuthorityByParentIdAndUserId(authority.getId(),id);
            //如果有子目录，进行递归，将子目录下的所有的子目录和权限进行封装
            if (authorityList.size()>0){
                children = findAuthorityByUserId(authorityList,id);
            }
            map.put("children",children);
            maps.add(map);
        }
        return maps;
    }

    public void addAuthority(Authority authority){
        authmapper.addAuthority(authority);
    }

    public List<Map<String,Object>> findAllAuthority(){
        List<Authority> list = authmapper.findAuthorityByParentId(0);
        List<Map<String, Object>> authorityList = findAuthority(list);
        return authorityList;
    }

    public List<Map<String, Object>> findAuthority(List<Authority> list){
        List<Map<String, Object>> maps = new ArrayList<>();
        //遍历目录
        for (Authority authority : list) {
            HashMap<String, Object> map = new HashMap<>();
            //子目录或权限
            List<Map<String, Object>> children = new ArrayList<>();
            //进行封装
            map.put("id",authority.getId());
            map.put("component",authority.getComponent());
            map.put("name",authority.getName());
            map.put("path",authority.getPath());
            map.put("type",authority.getType());
            map.put("parentId",authority.getParentId());
            map.put("order",authority.getOrder());
            map.put("icon",authority.getIcon());
            List<Authority> authorityList = authmapper.findAuthorityByParentId(authority.getId());
            //如果有子目录，进行递归，将子目录下的所有的子目录和权限进行封装
            if (authorityList.size()>0){
                children = findAuthority(authorityList);
            }
            map.put("children",children);
            maps.add(map);
        }
        return maps;
    }

    public void updateAuthority(Authority authority) {
        authmapper.updateAuthority(authority);
    }

    public void deleteAuthority(Integer id) {
        List<Integer> authorityIds = findAllAuthorityByParentId(id);
        if (authorityIds!=null){
            authorityIds.forEach(authorityId -> authmapper.deleteAuthority(authorityId));
        }
        authmapper.deleteAuthority(id);
    }

    private List<Integer> findAllAuthorityByParentId(Integer id) {
        //根据parentId查找权限
        List<Authority> authorityList = authmapper.findAuthorityByParentId(id);
        //判断是否有子权限
        if (authorityList==null||authorityList.size()==0){
            //没有返回null
            return null;
        }else {
            //有就创建一个权限的id集合
            ArrayList<Integer> AuthorityIds = new ArrayList<>();
            //遍历权限集合
            for (Authority authority : authorityList) {
                //递归
                List<Integer> list = findAllAuthorityByParentId(authority.getId());
                //将权限的id添加到集合中
                AuthorityIds.add(authority.getId());
            }
            return AuthorityIds;
        }
    }
}

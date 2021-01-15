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
        List<Authority> list = authmapper.findAuthorityByParentId(0,id);
        List<Map<String, Object>> resultList = findAuthority(list,id);
        return resultList;
    }

    public List<Map<String, Object>> findAuthority(List<Authority> list,Integer id){
        List<Map<String, Object>> maps = new ArrayList<>();
        for (Authority authority : list) {
            HashMap<String, Object> map = new HashMap<>();
            List<Map<String, Object>> children = new ArrayList<>();
            map.put("id",authority.getId());
            map.put("component",authority.getComponent());
            map.put("name",authority.getName());
            map.put("path",authority.getPath());
            map.put("type",authority.getType());
            map.put("parent_id",authority.getParentId());
            map.put("order",authority.getOrder());
            map.put("icon",authority.getIcon());
            List<Authority> authorityList = authmapper.findAuthorityByParentId(authority.getId(),id);
            if (authorityList.size()>0){
                children = findAuthority(authorityList,id);
            }
            map.put("children",children);
            maps.add(map);
        }
        return maps;
    }

}

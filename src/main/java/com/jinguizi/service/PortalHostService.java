package com.jinguizi.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinguizi.bean.AuditHostParams;
import com.jinguizi.bean.PortalHostModel;
import com.jinguizi.bean.SearchHostParams;
import com.jinguizi.bean.User;
import com.jinguizi.config.ExcelUtil;
import com.jinguizi.config.JwtUtil;
import com.jinguizi.mapper.PortalHostMapper;
import com.jinguizi.mapper.UserMapper;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Title: PotalHostService
 * @Description:
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2021/1/13  14:44
 */
@Service
@Transactional
public class PortalHostService {
    @Resource
    PortalHostMapper portalHostMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    JwtUtil jwtUtil;

    public PageInfo<PortalHostModel> searchHost(SearchHostParams searchHostParams) {

        PageHelper.startPage(searchHostParams.getPageNum(),searchHostParams.getPageSize());
        List<PortalHostModel> list= portalHostMapper.searchHost(searchHostParams);
        PageInfo<PortalHostModel> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public int insertPortalHostModel(PortalHostModel portalHostModel, HttpServletRequest request) throws IOException {
        String token = request.getHeader("token");
        Claims claims = jwtUtil.parseJWT(token);
        portalHostModel.setModeId("");
        portalHostModel.setUserId(Integer.parseInt(claims.getId()));
        portalHostModel.setUpdateDate(new Date(System.currentTimeMillis()));
        portalHostModel.setLinkContent("");
        portalHostModel.setState(0);
        portalHostModel.setHostClass("移动游戏");
        return portalHostMapper.insertPortalHostModel(portalHostModel);
    }

    public int updatePortalHostModelByModeId(PortalHostModel portalHostModel, HttpServletRequest request) throws IOException {
        String token = request.getHeader("token");
        Claims claims = jwtUtil.parseJWT(token);
        portalHostModel.setModeId("");
        portalHostModel.setUserId(Integer.parseInt(claims.getId()));
        portalHostModel.setUpdateDate(new Date(System.currentTimeMillis()));
        portalHostModel.setLinkContent("");
        portalHostModel.setState(1);
        portalHostModel.setHostClass("移动游戏");
        return portalHostMapper.updatePortalHostModelByModeId(portalHostModel);
    }

    public void export(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getHeader("token");
        Claims claims = jwtUtil.parseJWT(token);
        User userById = userMapper.findUserById(Integer.parseInt(claims.getId()));
        List<PortalHostModel> portalHostModels=null;
        if(userById.getUserright()==1){
            portalHostModels = portalHostMapper.searchHost(new SearchHostParams());
        }else{
            SearchHostParams searchHostParams = new SearchHostParams();
            searchHostParams.setUserId(Integer.parseInt(claims.getId()));
            portalHostModels = portalHostMapper.searchHost(searchHostParams);
        }

        if(portalHostModels.size()>0)
        {
            ExcelUtil.exportExcel(portalHostModels,null,"移动游戏",PortalHostModel.class,"game_host.xls",response);
        }
    }

    public List<PortalHostModel> findPortalModelByState() {

        return portalHostMapper.findPortalModelByState();
    }

    public int auditHost(AuditHostParams auditHostParams) {

        return portalHostMapper.auditHost(auditHostParams);
    }
}

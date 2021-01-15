package com.jinguizi.controller;

import com.github.pagehelper.PageInfo;
import com.jinguizi.bean.AuditHostParams;
import com.jinguizi.bean.PortalHostModel;
import com.jinguizi.bean.SearchHostParams;
import com.jinguizi.config.ExcelUtil;
import com.jinguizi.config.Result;
import com.jinguizi.config.ResultCode;
import com.jinguizi.service.PortalHostService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Title: PortalHostController
 * @Description:火狼网络管理平台
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2021/1/13  14:42
 */
@RestController
@RequestMapping("jinguizi/model")
public class PortalHostController {
    @Resource
    PortalHostService portalHostService;


    /**
     * 首页查看数据信息
     * @param searchHostParams
     * @return Result
     */
    @PostMapping("search")
    public Result searchHost(@RequestBody SearchHostParams searchHostParams){
        PageInfo<PortalHostModel> pageInfo=portalHostService.searchHost(searchHostParams);
        return Result.success(pageInfo);
    }

    /**
     * 添加数据
     * @param portalHostModel
     * @return Result
     */
    @PostMapping("editPortalHostModel")
    public Result insertPortalHostModel(@RequestBody PortalHostModel portalHostModel, HttpServletRequest request) throws IOException {
        int a=0;
        if(portalHostModel.getId()>0){
            a=portalHostService.insertPortalHostModel(portalHostModel,request);

        }else{
            a=portalHostService.updatePortalHostModelByModeId(portalHostModel,request);
        }
        if(a>0){
            return Result.success(ResultCode.SUCCESS);
        }else{
            return Result.failure(ResultCode.FAIL);
        }
    }

    /**
     * 导出文件
     * @return Result
     */
    @PostMapping("exportHost")
    public Result exportHost(HttpServletRequest request, HttpServletResponse response){
        try {
            portalHostService.export(request,response);
            return Result.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(ResultCode.FAIL);
        }
    }

    /**
     * 导出文件
     * @param uploadFile
     * @return Result
     */
    @PostMapping("importHost")
    public Result importHost(@RequestParam("upload_file") MultipartFile uploadFile, HttpServletRequest request) throws IOException {
        if (uploadFile.isEmpty()) {
            return Result.failure(ResultCode.ERROR_UPLOAD_EMPTY);
        }

        List<PortalHostModel> portalHostModels = ExcelUtil.importExcel(uploadFile,0,1,PortalHostModel.class);
        if(portalHostModels.size()>0) {
            for (PortalHostModel portalHostModel : portalHostModels) {
                portalHostService.updatePortalHostModelByModeId(portalHostModel,request);
            }
            return Result.success(ResultCode.SUCCESS);
        }
        else {
            return Result.failure(ResultCode.ERROR_UPLOAD);
        }
    }


    /**
     * 查询待审核的数据
     * @return Result
     */
    @PostMapping("findPortalModelByState")
    public Result findPortalModelByState(){
        List<PortalHostModel> list=portalHostService.findPortalModelByState();
        return Result.success(list);
    }

    /**
     * 审核数据
     * @param auditHostParams
     * @return Result
     */
    @PostMapping("audit")
    public Result auditHost(AuditHostParams auditHostParams){
        int a=portalHostService.auditHost(auditHostParams);
        if(a>0){
            return Result.success(ResultCode.SUCCESS);
        }else{
            return Result.failure(ResultCode.FAIL);
        }
    }


}

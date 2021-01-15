package com.jinguizi.controller;

import com.github.pagehelper.PageInfo;
import com.jinguizi.bean.ModelPlan;
import com.jinguizi.bean.PageResult;
import com.jinguizi.config.JwtUtil;
import com.jinguizi.config.Log;
import com.jinguizi.config.Result;
import com.jinguizi.service.ModelPlanService;
import io.jsonwebtoken.Claims;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @Title: ModelPlanController
 * @Description:运营数据管理
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2020/12/23  15:27
 */
@RestController
@RequestMapping("jinguizi/modelPlan")
public class ModelPlanController {
    @Resource
    ModelPlanService modelPlanService;


    /**
     * 查询运营数据
     * @param pageResult 统一参数接收类 pageNum 页码 pageSize 每页显示数据 modelPlanName 投放计划名称 maxNum 获客成本最大值 minNum 获客成本最小值 endTime 结束时间 beginTime 开始时间 channel 通道类型
     * @return Result 将查询出来的数据封装进PageInfo，并通过Result工具类返回
     */
    @PostMapping("findModelPlanByName")
    public Result findModelPlanByName(@RequestBody PageResult pageResult) throws ParseException {
        PageInfo<ModelPlan> pageInfo = modelPlanService.findModelPlanByName(pageResult);
        return Result.success(pageInfo);
    }


    /**
     * 上传文件
     * @param test 上传的文件
     * @return Result 如果上传出现异常，将异常信息通过Result返回，否则返回成功
     */
    @Log("上传文件")
    @PostMapping("upload")
    public Result upload(MultipartFile test,HttpServletRequest request){
        //获取我们的文件名称，存进session中，用于做aop向syslog数据表中记录操作
        request.getSession().setAttribute("fileName",test.getOriginalFilename());
        return modelPlanService.addModelPlan(request,test);
    }
}

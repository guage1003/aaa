package com.jinguizi.controller;

import com.github.pagehelper.PageInfo;
import com.jinguizi.bean.PageResult;
import com.jinguizi.bean.SelectByExampleParams;
import com.jinguizi.bean.SyncModelPlan;
import com.jinguizi.bean.SyncModelPlanVO;
import com.jinguizi.config.Log;
import com.jinguizi.config.Result;
import com.jinguizi.config.ResultCode;
import com.jinguizi.service.SyncModelPlanService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @Title: SyncModelPlanController
 * @Description:模型标签管理
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2021/1/7  14:28
 */
@RestController
@RequestMapping("jinguizi/sync")
public class SyncModelPlanController {
    @Resource
    SyncModelPlanService syncModelPlanService;

    /**
     * 根据条件查询
     * @param pageResult 统一参数接收类 pageNum 页码 pageSize 每页显示数据 modelPlanName 投放计划名称 beginTime开始时间 endTime 结束时间 cp 甲方
     * @return Result 将查询出来的数据集合封装进PageInfo，通过Result工具类返回
     */
    @PostMapping("selectByExample")
    public Result selectByExample(@RequestBody SelectByExampleParams pageResult, HttpServletRequest request) throws IOException, ParseException {
        System.out.println(pageResult.getPageNum()+"----"+pageResult.getPageSize());
        PageInfo<SyncModelPlanVO> syncModelPlanList = syncModelPlanService.selectByExample(pageResult,request);
        System.out.println(syncModelPlanList.getList().size());
        return Result.success(syncModelPlanList);

    }

    /**
     * 修改标签模型数据
     * @param syncModelPlan 根据id修改付费等级和自定义标签
     * @return Result 有异常返回自定义状态码，否则返回成功
     */
    @Log("修改标签模型数据")
    @PostMapping("updateSyncByExample")
    public Result updateSyncByExample(@RequestBody  SyncModelPlan syncModelPlan,HttpServletRequest request){
        try {
            syncModelPlanService.updateSyncByExample(syncModelPlan,request);
            return Result.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(ResultCode.FAIL);
        }
    }

    /**
     * 查询所有甲方名称
     * @return Result 将查询出来所有去重后的甲方，通过Result工具类将集合返回
     */
    @PostMapping("findCpAll")
    public Result findCpAll(){
        try {
            List<String> list=syncModelPlanService.findCpAll();
            return Result.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(ResultCode.FAIL);
        }
    }
}

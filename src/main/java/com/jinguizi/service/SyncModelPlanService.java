package com.jinguizi.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinguizi.bean.PageResult;
import com.jinguizi.bean.SelectByExampleParams;
import com.jinguizi.bean.SyncModelPlan;
import com.jinguizi.bean.SyncModelPlanVO;
import com.jinguizi.config.CheckResult;
import com.jinguizi.config.JwtUtil;
import com.jinguizi.mapper.SyncModelPlanMapper;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Title: SyncModelPlanService
 * @Description:
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2021/1/7  14:29
 */
@Service
@Transactional
public class SyncModelPlanService {
    @Resource
    SyncModelPlanMapper syncModelPlanMapper;

    @Resource
    JwtUtil jwtUtil;

    public PageInfo<SyncModelPlanVO> selectByExample(SelectByExampleParams pageResult, HttpServletRequest request) throws IOException, ParseException {

        SyncModelPlanVO syncModelPlan = new SyncModelPlanVO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //判断结束时间是不是为空，不为空的话对他进行格式化并存进实体类，否则他查询则不按当天开始，开始时间也是一样
        if(pageResult.getEndTime()!=null){
            String format2 = sdf.format(pageResult.getEndTime());
            syncModelPlan.setMaxTime(format2);
        }
        if(pageResult.getBeginTime()!=null){
            String format1 = sdf.format(pageResult.getBeginTime());
            syncModelPlan.setMinTime(format1);
        }

        syncModelPlan.setCp(pageResult.getCp());
        syncModelPlan.setName(pageResult.getName());

        PageHelper.startPage(pageResult.getPageNum(), pageResult.getPageSize());
        List<SyncModelPlanVO> syncModelPlanVOS = syncModelPlanMapper.selectByExample(syncModelPlan);
        PageInfo<SyncModelPlanVO> syncModelPlanVOPageInfo = new PageInfo<>(syncModelPlanVOS);
        return syncModelPlanVOPageInfo;
    }

    public void updateSyncByExample(SyncModelPlan syncModelPlan,HttpServletRequest request) throws IOException {
        String token = request.getHeader("token");
        CheckResult checkResult = jwtUtil.validateJWT(token);
        Claims claims = jwtUtil.parseJWT(token);
        String id = claims.getId();
        syncModelPlan.setUserId(id);
        syncModelPlan.setUpdateTime(new Date());
        syncModelPlanMapper.updateSyncByExample(syncModelPlan);
    }


    public List<String> findCpAll() {

        List<SyncModelPlan> list = syncModelPlanMapper.findCpAll();
        ArrayList<String> list1 = new ArrayList<>();
        for (SyncModelPlan syncModelPlan : list) {
            list1.add(syncModelPlan.getCp());
        }
        return list1;
    }
}

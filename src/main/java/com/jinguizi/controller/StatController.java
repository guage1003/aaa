package com.jinguizi.controller;

import com.jinguizi.bean.StatVO;
import com.jinguizi.config.Result;
import com.jinguizi.config.ResultCode;
import com.jinguizi.service.StatService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Title: StatController
 * @Description:首页数据统计
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2020/12/23  14:22
 */
//@CrossOrigin
@RestController
@RequestMapping("jinguizi/stat")
public class StatController {

    @Resource
    StatService statService;

    /**
     * 查询七天数据
     * @return Result 将查询7天的数据封装进集合，通过Result工具类返回
     */
    @PostMapping("modelPlanCount")
    public Result modelPlanCount(){
        try {
            List<StatVO> list = statService.modelPlanCount();
            //我们将查询出来的结果中的日期，对他进行格式化渲染给前台，格式为:(xx月xx日)
            SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
            for (StatVO statVO : list) {
                statVO.setUploadTime(sdf.format(statVO.getDateTime()));
            }
            return Result.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(ResultCode.ERROR_STAT);
        }
    }
}

package com.jinguizi.mapper;

import com.jinguizi.bean.SyncModelPlan;
import com.jinguizi.bean.SyncModelPlanVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: SyncModelPlanMapper
 * @Description:
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2021/1/7  14:27
 */
@Mapper
public interface SyncModelPlanMapper {

    List<SyncModelPlanVO> selectByExample(SyncModelPlanVO syncModelPlan);

    void updateSyncByExample(SyncModelPlan syncModelPlan);

    List<SyncModelPlan> findCpAll();
}

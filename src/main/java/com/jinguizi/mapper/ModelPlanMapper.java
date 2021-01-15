package com.jinguizi.mapper;

import com.jinguizi.bean.ModelPlan;
import com.jinguizi.bean.PageResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @Title: ModelPlanMapper
 * @Description:
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2020/12/23  15:27
 */
@Mapper
public interface ModelPlanMapper {

    List<ModelPlan> findModelPlanByName(PageResult pageResult);

    void addModelPlan(List<ModelPlan> list);

    void deleteFile(Integer fileId);

    List<ModelPlan> selectAll();
}

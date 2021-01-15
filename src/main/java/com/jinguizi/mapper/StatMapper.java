package com.jinguizi.mapper;

import com.jinguizi.bean.StatVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @Title: StatMapper
 * @Description:
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2020/12/23  14:23
 */
@Mapper
public interface StatMapper {
    List<StatVO> modelPlanCount();
}

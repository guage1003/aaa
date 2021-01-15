package com.jinguizi.service;

import com.jinguizi.bean.StatVO;
import com.jinguizi.mapper.StatMapper;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: StatService
 * @Description:
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2020/12/23  14:23
 */
@Service
@Transactional
public class StatService {
    @Resource
    StatMapper statMapper;

    public List<StatVO>  modelPlanCount(){
        return statMapper.modelPlanCount();
    }
}

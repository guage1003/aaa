package com.jinguizi.mapper;

import com.jinguizi.bean.AuditHostParams;
import com.jinguizi.bean.PortalHostModel;
import com.jinguizi.bean.SearchHostParams;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: PortalHostMapper
 * @Description:
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2021/1/13  14:51
 */
@Mapper
public interface PortalHostMapper {
    List<PortalHostModel> searchHost(SearchHostParams searchHostParams);

    int insertPortalHostModel(PortalHostModel portalHostModel);

    int updatePortalHostModelByModeId(PortalHostModel portalHostModel);

    List<PortalHostModel> findPortalModelByState();

    int auditHost(AuditHostParams auditHostParams);
}

package com.jinguizi.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinguizi.bean.FileDetail;
import com.jinguizi.bean.FileDetailVO;
import com.jinguizi.config.Result;
import com.jinguizi.mapper.FileDetailMapper;
import com.jinguizi.mapper.ModelPlanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @Title: FileDetailService
 * @Description:
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2020/12/23  18:34
 */
@Service
@Transactional
public class FileDetailService {
    @Resource
    FileDetailMapper fileDetailMapper;

    @Resource
    ModelPlanMapper modelPlanMapper;

    public PageInfo<FileDetailVO> findFileDetail(String fileName,
                                                 Integer pageNum,
                                                 Integer pageSize){
        FileDetail fileDetail = new FileDetail();
        fileDetail.setFileName(fileName);
        PageHelper.startPage(pageNum,pageSize);
        List<FileDetailVO> list=fileDetailMapper.findFileDetail(fileDetail);
        PageInfo<FileDetailVO> fileDetailPageInfo = new PageInfo<FileDetailVO>(list);
        return fileDetailPageInfo;
    }

    public void deleteFile(Integer fileId) {
        //这时我们不仅要删除file_detail表中的数据，也要删除model_plan表中的数据，因为file_detail描述的信息就是model_plan对应的数据
        fileDetailMapper.deleteFile(fileId);
        modelPlanMapper.deleteFile(fileId);
    }

    public FileDetail findFileNameById(Integer fileId) {
        return fileDetailMapper.findFileNameById(fileId);
    }
}

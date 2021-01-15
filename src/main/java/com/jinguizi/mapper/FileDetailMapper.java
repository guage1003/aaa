package com.jinguizi.mapper;

import com.jinguizi.bean.FileDetail;
import com.jinguizi.bean.FileDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: FileDetailMapper
 * @Description:
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2020/12/23  18:30
 */
@Mapper
public interface FileDetailMapper {
    void addFileDetail(FileDetail fileDetail);

    List<FileDetailVO> findFileDetail(FileDetail fileDetail);

    int findMaxId();

    void deleteFile(Integer fileId);

    FileDetail findFileNameById(Integer fileId);
}

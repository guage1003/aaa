package com.jinguizi.controller;

import com.github.pagehelper.PageInfo;
import com.jinguizi.bean.*;
import com.jinguizi.config.Log;
import com.jinguizi.config.Result;
import com.jinguizi.config.ResultCode;
import com.jinguizi.service.FileDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Title: FileDetailController
 * @Description:文件操作记录
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2020/12/23  18:33
 */
@RestController
@RequestMapping("jinguizi/fileDetail")
public class FileDetailController {
    @Resource
    FileDetailService fileDetailService;

    /**
     * 文件操作查询
     * @param pageResult 统一参数接收类  fileName 文件名称 pageNum 页码 pageSize 每页显示数据
     * @return Result 将查询出来的文件操作信息集合封装进PageInfo，通过Result工具类返回
     */
    @PostMapping("findFileDetail")
    public Result findFileDetail(@RequestBody FindDetailParams pageResult) {
        PageInfo<FileDetailVO> pageInfo = fileDetailService.findFileDetail(pageResult.getFileName(), pageResult.getPageNum(), pageResult.getPageSize());
        return Result.success(pageInfo);
    }

    /**
     * 删除文件
     * @param pageResult 统一参数接收类 Integer fileId 文件id
     * @return Result 如果程序出现异常，通过Result工具类返回我们自定义的状态码，否则返回成功
     */
    @Log("删除文件")
    @PostMapping("deleteFileById")
    public Result deleteFile(@RequestBody DeleteFileByIdParams pageResult, HttpServletRequest request) {
        try {
            FileDetail fileDetail = fileDetailService.findFileNameById(pageResult.getFileId());
            //我们将文件名称存进session，用于我们syslog数据表中可以记录删除的是哪个文件
            request.getSession().setAttribute("fileName", fileDetail.getFileName());
            fileDetailService.deleteFile(pageResult.getFileId());
            return Result.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(ResultCode.FAIL);
        }
    }
}

package com.vedu.oss.controller;

import com.vedu.commonutils.Result;
import com.vedu.eduservice.entity.EduTeacher;
import com.vedu.eduservice.service.EduTeacherService;
import com.vedu.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2020-10-21 16:51
 **/
@Api(description="阿里云文件管理")
@RestController
@RequestMapping("/eduoss/file")
@CrossOrigin //跨域
public class FileController {
    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     *
     * @param file
     */
    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public Result upload(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {

        String uploadUrl = fileService.upload(file);
        //返回r对象
        return Result.ok().message("文件上传成功").data("url", uploadUrl);
    }
}

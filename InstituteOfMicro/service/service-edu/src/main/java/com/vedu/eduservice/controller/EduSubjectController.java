package com.vedu.eduservice.controller;


import com.vedu.common.Result;
import com.vedu.eduservice.entity.subject.OneSubject;
import com.vedu.eduservice.service.EduSubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author lixiande
 * @since 2020-10-23
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("addSubject")
    public Result addSubject(MultipartFile file) {
        //1 获取上传的excel文件 MultipartFile
        //返回错误提示信息
        eduSubjectService.importSubjectData(file, eduSubjectService);
        //判断返回集合是否为空
        return Result.ok();
    }

    @ApiOperation(value = "嵌套数据列表")
    @GetMapping("")
    public Result nestedList(){

        List<OneSubject> subjectNestedVoList = eduSubjectService.nestedList();
        return Result.ok().data("items", subjectNestedVoList);
    }
}


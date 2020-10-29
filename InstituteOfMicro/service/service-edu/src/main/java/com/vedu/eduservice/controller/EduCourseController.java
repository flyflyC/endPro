package com.vedu.eduservice.controller;


import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vedu.common.Result;
import com.vedu.eduservice.entity.EduCourse;
import com.vedu.eduservice.entity.vo.CourseInfoForm;
import com.vedu.eduservice.entity.vo.CoursePublishForm;
import com.vedu.eduservice.entity.vo.CourseQuery;
import com.vedu.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author lixiande
 * @since 2020-10-24
 */
@Api(description = "课程管理")
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;


    @DeleteMapping("removeCourseById/{courseId}")
    public Result removeCourseById(@PathVariable String courseId){
        eduCourseService.removeCourse(courseId);
        return Result.ok();
    }

    /**
     * 课程分页查询
     * @param current
     * @param limit
     * @param courseQuery
     * @return
     */
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public Result pageQuery(@ApiParam(name = "current",value = "当前页码",required = true)
                            @PathVariable Long current,
                            @ApiParam(name = "limit",value = "每页记录数",required = true)
                            @PathVariable Long limit,
                            @ApiParam(name = "teacherQuery",value = "查询对象",required = false)
                            @RequestBody CourseQuery courseQuery){
        System.out.println("*******getTitle:" + courseQuery.getTitle() +
                "  getTeacherId:" + courseQuery.getTeacherId() +
                "  getSubjectId:" + courseQuery.getSubjectId() +
                "  getSubjectParentId:" + courseQuery.getSubjectParentId() + "  ******");
        // 创建 page 对象
        Page<EduCourse> pageParam = new Page<>(current,limit);
        // 根据查询条件，进行查询，自建方法
        eduCourseService.pageQuery(pageParam,courseQuery);
        // 获取查询结果总数
        long total = pageParam.getTotal();
        // 进行结果集的封装
        List<EduCourse> records = pageParam.getRecords();
        return Result.ok().data("total",total).data("records",records);
    }
    /**
     * 查询所有课程信息
     * @return
     */
    @GetMapping("getAllCourseInfo")
    public Result getAllCourseInfo(){
        List<EduCourse> eduCourses = eduCourseService.list(null);
        return Result.ok().data("items",eduCourses);
    }

    /**
     * 增加课程
     * @param courseInfoForm
     * @return
     */
    @ApiOperation(value = "新增课程")
    @PostMapping("saveCourseInfo")
    public Result saveCourseInfo(
            @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm){

        String courseId = eduCourseService.saveCourseInfo(courseInfoForm);
        if(!StringUtils.isEmpty(courseId)){
            return Result.ok().data("courseId", courseId);
        }else{
            return Result.error().message("保存失败");
        }
    }

    /**
     * 根据课程id获取课程信息
     * @param courseId
     * @return
     */
    @GetMapping("courseInfo/{courseId}")
    public Result getInfo(@PathVariable String courseId){
        CourseInfoForm courseInfoFormById = eduCourseService.getCourseInfoFormById(courseId);
        return Result.ok().data("items",courseInfoFormById);
    }

    /**
     * 更新课程信息
     * @param courseInfoForm
     * @return
     */
    @ApiOperation(value = "更新课程")
    @PutMapping("updateCourseInfo")
    public Result updateCourseInfoById(
            @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm){

        eduCourseService.updateCourseInfoById(courseInfoForm);
        return Result.ok();
    }

    /**
     * 发布课程页面中课程信息的获取 id
     * @param id
     * @return
     */
    @GetMapping("getCoursePublishForm/{id}")
    public Result getCoursePublishForm(@PathVariable String id){
        CoursePublishForm coursePublishForm = eduCourseService.getCoursePublishForm(id);
        return Result.ok().data("item",coursePublishForm);
    }

    /**
     * 最终发布课程
     * @param id
     * @return
     */
    @PutMapping("publishCourseById/{id}")
    public Result publishCourseById(@PathVariable String id){
        eduCourseService.publishCourseById(id);
        return Result.ok();
    }
}


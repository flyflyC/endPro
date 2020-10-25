package com.vedu.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vedu.commonutils.Result;
import com.vedu.eduservice.entity.EduTeacher;
import com.vedu.eduservice.entity.vo.TeacherQuery;
import com.vedu.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author lixiande
 * @since 2020-10-15
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin // 解决跨域请求
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "查询所有")
    @GetMapping("findAll")
    public Result findAll(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return Result.ok().data("items",list);
    }

    @ApiOperation(value = "逻辑删除")
    @DeleteMapping("{id}")
        public Result removeById(@ApiParam(name = "id",value = "讲师id",required = true)
        @PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if(flag){
            return Result.ok();
        }else{
            return Result.error();
        }
    }
    // current 表示当前页 limit 表示数据展示数量
    @ApiOperation(value = "讲师分页")
    @GetMapping("pageTeacher/{current}/{limit}")
    public Result pageList(
            @ApiParam(name = "current",value = "当前页码",required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit",value = "每页记录数",required = true)
            @PathVariable Long limit){
        // 需要在配置类中添加分页插件
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        eduTeacherService.page(pageTeacher, null);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return Result.ok().data("total",total).data("rows",records);

    }

    /**
     * 通过TeacherQuery对象添加查询条件
     * @param current
     * @param limit
     * @param teacherQuery
     * @return
     */
    @ApiOperation(value = "分页讲师列表")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Result pageQuery(@ApiParam(name = "current",value = "当前页码",required = true)
                                @PathVariable Long current,
                            @ApiParam(name = "limit",value = "每页记录数",required = true)
                                @PathVariable Long limit,
                            @ApiParam(name = "teacherQuery",value = "查询对象",required = false)
                                @RequestBody TeacherQuery teacherQuery){
        System.out.println(teacherQuery.getName() + teacherQuery.getLevel() + "****");
        // 创建 page 对象
        Page<EduTeacher> pageParam = new Page<>(current,limit);
        // 根据查询条件，进行查询，自建方法
        eduTeacherService.pageQuery(pageParam,teacherQuery);
        // 获取查询结果总数
        long total = pageParam.getTotal();
        // 进行结果集的封装
        List<EduTeacher> records = pageParam.getRecords();
        return Result.ok().data("total",total).data("records",records);
    }

    @ApiOperation(value = "讲师添加操作")
    @PostMapping("addteacher")
    public Result addteacher(
            @ApiParam(name = "teacher",value = "讲师对象",required = true)
            @RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if(save){
            return Result.ok();
        }else{
            return Result.error();
        }
    }

    @ApiOperation(value = "讲师id查询")
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(
            @ApiParam(name = "id",value = "讲师id",required = true)
            @PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return Result.ok().data("teacher",teacher);
    }

    @ApiOperation(value = "修改讲师信息")
    @PostMapping("updateTeacher")
    public Result updateTeacher(
            @ApiParam(name = "teacher",value = "讲师对象",required = true)
            @RequestBody EduTeacher teacher){
        boolean flag = eduTeacherService.updateById(teacher);
        if(flag){
            return Result.ok();
        }else{
            return Result.error();
        }
    }
}


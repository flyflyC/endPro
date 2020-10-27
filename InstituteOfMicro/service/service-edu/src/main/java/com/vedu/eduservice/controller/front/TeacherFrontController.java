package com.vedu.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vedu.commonutils.Result;
import com.vedu.eduservice.entity.EduTeacher;
import com.vedu.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/eduService/teacherfront")
@CrossOrigin
public class TeacherFrontController {
    @Autowired
    private EduTeacherService teacherService;

    @PostMapping("/getTeacherFrontList/{page}/{limit}")
    public Result getTeacherFrontList(@PathVariable long page, @PathVariable long limit){
        Page<EduTeacher> pageTeacher = new Page<>(page,limit);
        Map<String,Object> map = teacherService.getTeacherFrontList(pageTeacher);
        return Result.ok().data(map);
    }

    @GetMapping("/getTeacherFrontInfo/{teacherId}")
    public Result getTeacherFrontInfo(@PathVariable String teacherId){
        EduTeacher eduTeacher = teacherService.getById(teacherId);
        //课程信息！！！
//        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();



        Map<String,Object> map = new HashMap<>();
        map.put("teacher",eduTeacher);
        return Result.ok().data(map);
    }
}

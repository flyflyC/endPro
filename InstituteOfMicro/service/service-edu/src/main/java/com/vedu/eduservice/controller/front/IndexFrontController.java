package com.vedu.eduservice.controller.front;

import com.vedu.commonutils.Result;
import com.vedu.eduservice.entity.EduCourse;
import com.vedu.eduservice.entity.EduTeacher;
import com.vedu.eduservice.service.EduCourseService;
import com.vedu.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    //查询前4条名师
    @GetMapping("/hotTeacher")
    public Result getHotTeacher(){
        List<EduTeacher> teacherList=teacherService.selectHotTeacher();
        HashMap<String, Object> map = new HashMap<>();
        map.put("teacherList",teacherList);
        return Result.ok().data(map);
    }

    //查询前八条热门课程！！！
    @GetMapping("/hotCourse")
    public Result getHotCourse(){
        List<EduCourse> courseList = courseService.selectHotCourse();
        HashMap<String, Object> map = new HashMap<>();
        map.put("eduList",courseList);
        return Result.ok().data(map);
    }



    //查询前八条热门课程，4条名师
 /*   @GetMapping("/index")
    public Result index(){
//        4条名师
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("teacherList",teacherList);

        //查询前八条热门课程!!

        return Result.ok().data(map);
    }*/
}

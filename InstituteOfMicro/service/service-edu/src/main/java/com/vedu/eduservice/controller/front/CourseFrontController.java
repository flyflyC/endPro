package com.vedu.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vedu.common.Result;
import com.vedu.common.ordervo.CourseWebVoOrder;
import com.vedu.eduservice.entity.EduCourse;
import com.vedu.eduservice.entity.frontvo.CourseFrontVo;
import com.vedu.eduservice.entity.frontvo.CourseWebVo;
import com.vedu.eduservice.entity.vo.ChapterVo;
import com.vedu.eduservice.service.EduChapterService;
import com.vedu.eduservice.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;
    //条件查询带分页查询课程
    @PostMapping("/getFrontCourseList/{page}/{limit}")
    public Result getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                     @RequestBody(required = false) CourseFrontVo courseFrontVo){
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String, Object> map = courseService.getCourseFrontList(pageCourse,courseFrontVo);
        return Result.ok().data(map);
    }

    @GetMapping("getFrontCourseInfo/{courseId}")
    public Result getFrontCourseInfo(@PathVariable String courseId){
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
        List<ChapterVo> chapterVideoList = chapterService.nestedList(courseId);
        HashMap<String,Object> map = new HashMap<>();
        map.put("courseWebVo",courseWebVo);
        map.put("chapterVideoList",chapterVideoList);
        return Result.ok().data(map);
    }

    //根据课程id查询课程信息
    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id){
        CourseWebVo courseInfo = courseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseInfo,courseWebVoOrder);
        return courseWebVoOrder;
    }
}

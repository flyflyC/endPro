package com.vedu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vedu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vedu.eduservice.entity.frontvo.CourseFrontVo;
import com.vedu.eduservice.entity.frontvo.CourseWebVo;
import com.vedu.eduservice.entity.vo.CourseInfoForm;
import com.vedu.eduservice.entity.vo.CoursePublishForm;
import com.vedu.eduservice.entity.vo.CourseQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author lixiande
 * @since 2020-10-24
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);
    // 根据id获取课程信息
    CourseInfoForm getCourseInfoFormById(String id);

    void updateCourseInfoById(CourseInfoForm courseInfoForm);

    CoursePublishForm getCoursePublishForm(String id);

    void publishCourseById(String id);

    void pageQuery(Page<EduCourse> page, CourseQuery courseQuery);

    void removeCourse(String courseId);

    List<EduCourse> selectHotCourse();

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}

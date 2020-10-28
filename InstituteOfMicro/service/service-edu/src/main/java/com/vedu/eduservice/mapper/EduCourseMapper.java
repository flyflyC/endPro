package com.vedu.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vedu.eduservice.entity.EduCourse;
import com.vedu.eduservice.entity.frontvo.CourseWebVo;
import com.vedu.eduservice.entity.vo.CoursePublishForm;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author lixiande
 * @since 2020-10-24
 */
@Mapper
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishForm selectCoursePublishFormById(String id);

    //根据课程id查询课程信息
    CourseWebVo getBaseCourseInfo(String courseId);
}

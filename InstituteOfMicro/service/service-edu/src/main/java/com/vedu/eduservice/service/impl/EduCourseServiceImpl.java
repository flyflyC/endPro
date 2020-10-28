package com.vedu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vedu.eduservice.entity.EduCourse;
import com.vedu.eduservice.entity.EduCourseDescription;
import com.vedu.eduservice.entity.frontvo.CourseFrontVo;
import com.vedu.eduservice.entity.frontvo.CourseWebVo;
import com.vedu.eduservice.entity.vo.CourseInfoForm;
import com.vedu.eduservice.entity.vo.CoursePublishForm;
import com.vedu.eduservice.entity.vo.CourseQuery;
import com.vedu.eduservice.mapper.EduCourseMapper;
import com.vedu.eduservice.service.EduChapterService;
import com.vedu.eduservice.service.EduCourseDescriptionService;
import com.vedu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vedu.eduservice.service.EduVideoService;
import com.vedu.servicebase.exceptionhandler.EduException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author lixiande
 * @since 2020-10-24
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService descriptionService;

    @Autowired(required = false)
    private EduCourseMapper eduCourseMapper;

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService eduChapterService;

    //查询课程信息
    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }

    @Cacheable(key = "'selectHotCourse'",value = "hotCourse")
    @Override
    public List<EduCourse> selectHotCourse() {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<EduCourse> list = baseMapper.selectList(wrapper);
        return list;
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageParam, CourseFrontVo courseFrontVo) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())){
            wrapper.eq("subject_parent_id",courseFrontVo.getSubjectParentId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectId())){
            wrapper.eq("subject_id",courseFrontVo.getSubjectId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())){
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())) {
            wrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getPriceSort())) {
            wrapper.orderByDesc("price");
        }

        baseMapper.selectPage(pageParam, wrapper);


        List<EduCourse> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }



    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {

        //保存课程基本信息
        EduCourse course = new EduCourse();
        course.setStatus(EduCourse.COURSE_DRAFT);
        BeanUtils.copyProperties(courseInfoForm, course);
        course.setIsDeleted(0);
        boolean resultCourseInfo = this.save(course);
        if (!resultCourseInfo) {
            throw new EduException(20001, "课程信息保存失败");
        }

        //保存课程详情信息
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId()); // 一对一关系，两表的id一致
        boolean resultDescription = descriptionService.save(courseDescription);
        if (!resultDescription) {
            throw new EduException(20001, "课程详情信息保存失败");
        }

        return course.getId();
    }

    @Override
    public CourseInfoForm getCourseInfoFormById(String id) {
        EduCourse eduCourse = baseMapper.selectById(id);
        if(eduCourse == null){
            throw new EduException(20001,"数据不存在");
        }

        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse,courseInfoForm);

        EduCourseDescription byId = descriptionService.getById(id);
        if(eduCourse != null){
            courseInfoForm.setDescription(byId.getDescription());
        }
        return courseInfoForm;
    }



    @Override
    public void updateCourseInfoById(CourseInfoForm courseInfoForm) {
        //保存课程基本信息
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, course);
        boolean resultCourseInfo = this.updateById(course);
        if(!resultCourseInfo){
            throw new EduException(20001, "课程信息保存失败");
        }

        //保存课程详情信息
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        boolean resultDescription = descriptionService.updateById(courseDescription);
        if(!resultDescription){
            throw new EduException(20001, "课程详情信息保存失败");
        }
    }

    @Override
    public CoursePublishForm getCoursePublishForm(String id) {
        CoursePublishForm coursePublishForm = eduCourseMapper.selectCoursePublishFormById(id);
        return coursePublishForm;
    }

    @Override
    public void publishCourseById(String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus(EduCourse.COURSE_NORMAL);
        boolean flag = this.updateById(eduCourse);
        if(!flag){
            throw new EduException(20001,"课程发布失败!");
        }
    }

    @Override
    public void pageQuery(Page<EduCourse> page, CourseQuery courseQuery) {
        // 创建查询条件对象
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        // 如果查询条件为空，则直接进行结果查询
        if(courseQuery == null){
            baseMapper.selectPage(page,wrapper);
            return;
        }
        // 进行查询条件获取
        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String subjectId = courseQuery.getSubjectId();
        // 判断各信息是否为空
        if(!StringUtils.isEmpty(title)){
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(teacherId)){
            wrapper.eq("teacher_id",teacherId);
        }
        if(!StringUtils.isEmpty(subjectParentId)){
            wrapper.eq("subject_parent_id",subjectParentId);
        }
        if(!StringUtils.isEmpty(subjectId)){
            wrapper.eq("subject_id",subjectId);
        }
        baseMapper.selectPage(page,wrapper);
    }

    @Override
    public void removeCourse(String courseId) {
        // 1、根据课程id删除小节信息
        eduVideoService.removeVideoById(courseId);
        // 2、根据课程id删除章节信息
        eduChapterService.removeChapterInfoById(courseId);
        // 3、根据课程id删除课程描述
        descriptionService.removeById(courseId);
        // 4、根据课程id删除课程自身
        baseMapper.deleteById(courseId);
    }
}

package com.vedu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vedu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vedu.eduservice.entity.vo.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author lixiande
 * @since 2020-10-15
 */
public interface EduTeacherService extends IService<EduTeacher> {

    public void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);
}

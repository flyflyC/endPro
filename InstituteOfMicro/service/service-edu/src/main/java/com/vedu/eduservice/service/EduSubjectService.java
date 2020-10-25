package com.vedu.eduservice.service;

import com.vedu.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vedu.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author lixiande
 * @since 2020-10-23
 */
public interface EduSubjectService extends IService<EduSubject> {

    void importSubjectData(MultipartFile file, EduSubjectService eduSubjectService);

    List<OneSubject> nestedList();
}

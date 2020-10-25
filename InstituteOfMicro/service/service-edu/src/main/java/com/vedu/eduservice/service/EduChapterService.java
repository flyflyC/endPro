package com.vedu.eduservice.service;

import com.vedu.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vedu.eduservice.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author lixiande
 * @since 2020-10-24
 */
public interface EduChapterService extends IService<EduChapter> {
    List<ChapterVo> nestedList(String courseId);

    boolean removeChapterById(String id);

    void removeChapterInfoById(String courseId);
}

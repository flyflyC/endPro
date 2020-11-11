package com.vedu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vedu.eduservice.entity.EduComment;

import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-19
 */
public interface EduCommentService extends IService<EduComment> {

    /**
     * 通过课程id分页查询课程评论
     * @param page
     * @param courseId
     * @return
     */
    Map<String, Object> getPageComment(Page<EduComment> page, String courseId);
}

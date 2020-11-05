package com.vedu.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vedu.blog.entity.Commentator;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-30
 */
public interface CommentatorService extends IService<Commentator> {

    /**
     * 分页获取博客评论
     * @param page
     * @param blogId
     * @return
     */
    Map<String, Object> getPageComment(Page<Commentator> page, String blogId);
}

package com.vedu.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vedu.blog.entity.Commentator;
import com.vedu.blog.mapper.CommentatorMapper;
import com.vedu.blog.service.CommentatorService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-30
 */
@Service
public class CommentatorServiceImpl extends ServiceImpl<CommentatorMapper, Commentator> implements CommentatorService {

    @Override
    public Map<String, Object> getPageComment(Page<Commentator> page, String blogId) {
        //构建条件
        QueryWrapper<Commentator> wrapper = new QueryWrapper<>();
        wrapper.eq("blog_id",blogId);
        baseMapper.selectPage(page,wrapper);

        //获取总条数
        Long total = page.getTotal();
        List<Commentator> records = page.getRecords();
        //封装数据
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("comments", records);
        map.put("current", page.getCurrent());
        map.put("pages", page.getPages());
        map.put("size", page.getSize());
        map.put("hasNext", page.hasNext());
        map.put("hasPrevious", page.hasPrevious());
        return map;
    }
}

package com.vedu.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vedu.blog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-30
 */
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {

}

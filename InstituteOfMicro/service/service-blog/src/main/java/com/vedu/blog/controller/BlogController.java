package com.vedu.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vedu.blog.entity.Blog;
import com.vedu.blog.entity.vo.QueryBlog;
import com.vedu.blog.service.BlogService;
import com.vedu.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-30
 */
@Api(description = "博客管理")
@RestController
@RequestMapping("/edublog/blog")
@CrossOrigin
public class BlogController {

    @Autowired
    private BlogService blogService;
    @ApiOperation(value = "所有博客列表")
    @GetMapping("findAll")
    public Result findAllBlog(){
        List<Blog> blogs = blogService.list(null);
        Map<String, Object> map = new HashMap<>();
        map.put("blogs",blogs);
        return Result.ok().data(map);
    }
    @ApiOperation(value = "博客详情页")
    @GetMapping("detail/{id}")
    public Result detail(@PathVariable("id") String id){
        Blog blog = blogService.getById(id);
        System.out.println(blog);
        System.out.println(id);
        //Assert.notNull(blog,"该博客已被删除");
        Map<String,Object> map = new HashMap<>();
        map.put("blog",blog);

        return Result.ok().data(map);
    }

    @ApiOperation(value = "根据id删除博客")
    @DeleteMapping("/delete/{id}")
    public Result removeBlog(@PathVariable String id){
        boolean flag = blogService.removeById(id);
        if (flag) {
            return Result.ok();
        } else {
            return Result.error().data("id",id);
        }
    }
    @ApiOperation(value = "分页查询")
    @PostMapping("/pageBlog/{current}/{limit}")
    public Result pageListTeacher(@PathVariable("current")Integer current,
                                  @PathVariable("limit") Integer limit){
        //创建page对象
        Page<Blog> page = new Page<>(current, limit);
        //调用方法实现分页
        blogService.page(page,null);
        //获取总条数
        Long total = page.getTotal();
        //返回对象集合
        List<Blog> blogs = page.getRecords();
        //封装数据
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows", blogs);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "根据条件分页查询")
    @PostMapping("pageBlogCondition/{current}/{limit}")
    public Result pageBlogCondition(@PathVariable("current") Integer current,
                                    @PathVariable("limit") Integer limit,
                                    @RequestBody(required = false) QueryBlog queryBlog) {
        Page<Blog> page = new Page<>(current, limit);
        //构件条件
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        //多条件组合查询
        //判断条件是否为空，如果不为空拼接条件
        String title = queryBlog.getTitle();
        String username = queryBlog.getUsername();
        if (!StringUtils.isEmpty(title)) {
            //构建条件
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(username)) {
            //构建条件
            wrapper.eq("username", username);
        }
        wrapper.orderByDesc("gmt_create");
        //调用方法实现分页条件查询
        blogService.page(page, wrapper);
        Long total = page.getTotal();
        //返回对象集合
        List<Blog> blogs = page.getRecords();
        System.out.println(blogs.toString());
        //封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", blogs);
        return Result.ok().data(map);
    }

    @PostMapping("updateBlog")
    public Result updateBlog(@RequestBody Blog blog){
        System.out.println(blog.toString());
        if (StringUtils.isEmpty(blog)){
            return Result.error();
        }
        boolean save = blogService.updateById(blog);
        if(save){
            return Result.ok();
        }else {
            return Result.error();
        }
    }
}


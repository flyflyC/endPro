package com.vedu.blog.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vedu.blog.client.UcentenClient;
import com.vedu.blog.entity.Blog;
import com.vedu.blog.entity.vo.QueryBlog;
import com.vedu.blog.service.BlogService;
import com.vedu.common.JwtUtils;
import com.vedu.common.Result;
import com.vedu.common.ordervo.UcenterMemberVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @ClassName BlogFrontController
 * @Author cai feifei
 * @date 2020.11.01 19:48
 * @Version
 */
@Api(description = "前台课程展示")
@RestController
@RequestMapping("/edublog/blog")
@CrossOrigin
public class BlogFrontController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UcentenClient ucentenClient;

    @ApiOperation(value = "获取个人博客")
    @GetMapping("/getBlogInfo")
    public Result getBlogInfo( HttpServletRequest request){
        //根据用户id判断用户是否已经登录
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(memberId)){
            return Result.error().message("要登录才能查看个人博客，亲先登录吧！");
        }
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("member_id",memberId);
        List<Blog> list = blogService.list(wrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("isBlog",list);

        return Result.ok().data(map);
    }
    @ApiOperation(value = "判断用户是否登录")
    @PostMapping("/isLogin")
    public Result isLogin(HttpServletRequest request){
        //根据用户id判断用户是否已经登录
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(memberId)){
            return Result.error().message("要登录才能编辑个人博客，亲先登录吧！");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("isLogin",1);
        map.put("memberid",memberId);
        return Result.ok().data(map);
    }
    @ApiOperation(value = "根据条件分页查询")
    @PostMapping("pageMyBlogCondition/{current}/{limit}")
    public Result pageMyBlogCondition(@PathVariable("current") Integer current,
                                    @PathVariable("limit") Integer limit,
                                    @RequestBody(required = false) QueryBlog queryBlog,
                                    HttpServletRequest request) {
        //根据用户id判断用户是否已经登录
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(memberId)){
            return Result.error().message("要登录才能查看个人博客，亲先登录吧！");
        }
        Page<Blog> page = new Page<>(current, limit);
        //构件条件
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("member_id",memberId);
        //多条件组合查询
        //判断条件是否为空，如果不为空拼接条件
        String title = queryBlog.getTitle();
        System.out.println(title);
        String username = queryBlog.getUsername();
        System.out.println(username);
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
        map.put("myBlog", blogs);
        return Result.ok().data(map);
    }
    @ApiOperation(value = "根据条件分页查询")
    @PostMapping("pageBlogFronCondition/{current}/{limit}")
    public Result pageBlogFronCondition(@PathVariable("current") Integer current,
                                    @PathVariable("limit") Integer limit,
                                    @RequestBody(required = false) QueryBlog queryBlog) {
        Page<Blog> page = new Page<>(current, limit);
        //构件条件
        System.out.println(current+"......"+limit);
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        //多条件组合查询
        //判断条件是否为空，如果不为空拼接条件
        String title = queryBlog.getTitle();
        System.out.println(title);
        String username = queryBlog.getUsername();
        System.out.println(username);
        if (!StringUtils.isEmpty(title)) {
            //构建条件
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(username)) {
            //构建条件
            wrapper.eq("username", username);
        }
        wrapper.eq("status", 0);
        wrapper.orderByDesc("gmt_create");
        //调用方法实现分页条件查询
        blogService.page(page, wrapper);
        Long total = page.getTotal();
        //返回对象集合
        List<Blog> blogs = page.getRecords();
        //System.out.println(blogs.toString());
        //封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", blogs);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "用户发布博客")
    @PostMapping("/addBlog")
    public Result addBlog(@RequestBody Blog blog,HttpServletRequest request) {
        //根据用户id判断用户是否已经登录
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(memberId)){
            return Result.error().message("要登录才能查看个人博客，亲先登录吧！");
        }
        System.out.println(blog.toString());
        if (StringUtils.isEmpty(blog)){
            return Result.error();
        }
        blog.setMemberId(memberId);
        UcenterMemberVo userInfoForCom = ucentenClient.getUserInfoOrder(memberId);
        System.out.println(userInfoForCom);
        blog.setUsername(userInfoForCom.getNickname());
        blog.setGoods(0);
        blog.setStatus(0);
        blog.setViews(0);
        blog.setComments(0);
        boolean save = blogService.save(blog);
        if(save){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    @ApiOperation(value = "用户保存博客")
    @PostMapping("/saveBlog")
    public Result saveBlog(@RequestBody Blog blog,HttpServletRequest request) {
        //根据用户id判断用户是否已经登录
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(memberId)){
            return Result.error().message("要登录才能保存个人博客，亲先登录吧！");
        }
        if (StringUtils.isEmpty(blog)){
            return Result.error();
        }
        System.out.println(blog.toString());
        if (!StringUtils.isEmpty(blog.getId())){
            boolean updateById = blogService.updateById(blog);
            if(updateById){
                return Result.ok();
            }else {
                return Result.error();
            }
        }else {
            blog.setMemberId(memberId);
            UcenterMemberVo userInfoForCom = ucentenClient.getUserInfoOrder(memberId);
            System.out.println(userInfoForCom);
            blog.setUsername(userInfoForCom.getNickname());
            blog.setGoods(0);
            //status：0表示发布，1表示未发布
            blog.setStatus(1);
            blog.setViews(0);
            blog.setComments(0);
            boolean save = blogService.save(blog);
            if(save){
                return Result.ok();
            }else {
                return Result.error();
            }
        }
    }
    @ApiOperation("修改并发布博客")
    @PostMapping("updateIssueBlog")
    public Result updateIssueBlog(@RequestBody Blog blog){
        System.out.println(blog.toString());
        if (StringUtils.isEmpty(blog)){
            return Result.error();
        }
        blog.setStatus(0);
        boolean save = blogService.updateById(blog);
        if(save){
            return Result.ok();
        }else {
            return Result.error();
        }
    }
}

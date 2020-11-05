package com.vedu.blog.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.vedu.blog.client.UcentenClient;
import com.vedu.blog.entity.Commentator;
import com.vedu.blog.service.CommentatorService;
import com.vedu.common.JwtUtils;
import com.vedu.common.Result;
import com.vedu.common.ordervo.UcenterMemberVo;
import com.vedu.servicebase.exceptionhandler.EduException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-30
 */
@Api(description = "博客评论管理")
@CrossOrigin
@RestController
@RequestMapping("/eduBlog/commentator")
public class CommentatorController {
    @Autowired
    private CommentatorService commentatorService;

    @Autowired
    private UcentenClient ucentenClient;

    @ApiOperation(value = "分页查询")
    @GetMapping("/pageComment/{current}/{limit}/{courseId}")
    public Result pageComment(@PathVariable("current")Integer current,
                              @PathVariable("limit") Integer limit, @PathVariable String blogId){
        //创建page对象
        Page<Commentator> page = new Page<>(current,limit);
        Map<String,Object> map = commentatorService.getPageComment(page,blogId);
        return Result.ok().data(map);
    }


    @ApiOperation(value = "添加评论")
    @PostMapping("/addComment")
    public Result addComment(@RequestBody Commentator comment, HttpServletRequest request){
        System.out.println(comment.toString());
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        System.out.println(memberId);
        if(StringUtils.isEmpty(memberId)) {
            return Result.error().code(20001).message("请登录");
        }
        comment.setMemberId(memberId);
        UcenterMemberVo userInfoForCom = ucentenClient.getUserInfoOrder(memberId);
        String nickName = userInfoForCom.getNickname();
        String avatar =  userInfoForCom.getAvatar();
        comment.setCname(nickName);

        System.out.println(comment.toString());
        boolean save = commentatorService.save(comment);
        if (!save){
            throw new EduException(20001,"添加评论失败");
        }
        return Result.ok();
    }

    @ApiOperation(value = "删除评论")
    @DeleteMapping("/addComment/{id}")
    public Result deleteComment(@PathVariable String id){
        boolean b = commentatorService.removeById(id);
        if (b) {
            return Result.ok();
        } else {
            return Result.error().message("删除失败！").data("id",id);
        }
    }
}


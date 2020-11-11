package com.vedu.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vedu.common.JwtUtils;
import com.vedu.common.Result;
import com.vedu.common.ordervo.UcenterMemberVo;
import com.vedu.eduservice.client.UcentenClient;
import com.vedu.eduservice.entity.EduComment;
import com.vedu.eduservice.service.EduCommentService;
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
 * 评论 前端控制器
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-19
 */
@Api(description = "课程评论管理")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/comment")
public class EduCommentController {

    @Autowired
    private EduCommentService commentService;

    @Autowired
    private UcentenClient ucentenClient;

    @ApiOperation(value = "分页查询")
    @GetMapping("/pageComment/{current}/{limit}/{courseId}")
    public Result pageComment(@PathVariable("current")Integer current,
                              @PathVariable("limit") Integer limit, @PathVariable String courseId){
        //创建page对象
        Page<EduComment> page = new Page<>(current,limit);
        Map<String,Object> map = commentService.getPageComment(page,courseId);
        return Result.ok().data(map);
    }


    @ApiOperation(value = "添加评论")
    @PostMapping("/addComment")
    public Result addComment(@RequestBody EduComment comment, HttpServletRequest request){
        System.out.println(comment.toString());
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        System.out.println(memberId);
        if(StringUtils.isEmpty(memberId)) {
            return Result.error().code(20001).message("请登录");
        }
        comment.setMemberId(memberId);
        UcenterMemberVo userInfoForCom = ucentenClient.getUserInfoForCom(memberId);
        String nickName = userInfoForCom.getNickname();
        String avatar =  userInfoForCom.getAvatar();
        comment.setAvatar(avatar);
        comment.setNickname(nickName);

        System.out.println(comment.toString());
        boolean save = commentService.save(comment);
        if (!save){
            throw new EduException(20001,"添加评论失败");
        }
        return Result.ok();
    }

    @ApiOperation(value = "删除评论")
    @DeleteMapping("/deleteComment/{id}")
    public Result deleteComment(@PathVariable String id){
        boolean b = commentService.removeById(id);
        if (b) {
            return Result.ok();
        } else {
            return Result.error().message("删除失败！").data("id",id);
        }
    }
}


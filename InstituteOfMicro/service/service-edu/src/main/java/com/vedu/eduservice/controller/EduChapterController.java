package com.vedu.eduservice.controller;


import com.vedu.commonutils.Result;
import com.vedu.eduservice.entity.EduChapter;
import com.vedu.eduservice.entity.vo.ChapterVo;
import com.vedu.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author lixiande
 * @since 2020-10-24
 */
@RestController
@RequestMapping("/eduservice/eduChapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;

    @GetMapping("nestList/{courseId}")
    public Result nestedListByCourseId(@PathVariable String courseId){
        List<ChapterVo> chapterVos = chapterService.nestedList(courseId);
        return Result.ok().data("items",chapterVos);
    }

    @GetMapping("getById/{id}")
    public Result getChapterById(@PathVariable String id){
        EduChapter chapter = chapterService.getById(id);
        return Result.ok().data("item",chapter);
    }

    @PostMapping("addChapter")
    public Result addChapter(@RequestBody EduChapter eduChapter){
        chapterService.save(eduChapter);
        return Result.ok();
    }

    @PutMapping("updateById")
    public Result updateById(@RequestBody EduChapter eduChapter){
        chapterService.updateById(eduChapter);
        return Result.ok();
    }

    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable String id){

        boolean flag = chapterService.removeChapterById(id);
        if (flag) {
            return Result.ok();
        }else{
            return Result.error().message("删除失败!");
        }
    }
}


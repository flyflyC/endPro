package com.vedu.eduservice.controller;


import com.vedu.common.Result;
import com.vedu.eduservice.entity.vo.VideoInfoForm;
import com.vedu.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author lixiande
 * @since 2020-10-24
 */
@RestController
@RequestMapping("/eduservice/eduVideo")
@CrossOrigin
public class EduVideoController {
    @Autowired
    EduVideoService eduVideoService;

    /**
     * 增加小节
     * @param videoInfoForm
     * @return
     */
    @PostMapping("saveInfo") // /eduservice/eduVideo/saveInfo
    public Result saveInfo(@RequestBody VideoInfoForm videoInfoForm){
        eduVideoService.saveInfo(videoInfoForm);
        return Result.ok();
    }

    /**
     * 根据id查询小节信息
     * @param id
     * @return
     */
    @GetMapping("getById/{id}")
    public Result getById(@PathVariable String id){
        VideoInfoForm videoInfoForm = eduVideoService.getInfoById(id);
        return Result.ok().data("item",videoInfoForm);
    }

    /**
     * 根据id更新小节信息
     * @param videoInfoForm
     * @return
     */
    @PutMapping("updateById")
    public Result updateById(@RequestBody VideoInfoForm videoInfoForm){
        eduVideoService.updateInfoById(videoInfoForm);
        return Result.ok();
    }

    /**
     * 根据id删除小节
     * @param id
     * @return
     */
    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable String id){
        eduVideoService.removeInfoById(id);
        return Result.ok();
    }
}


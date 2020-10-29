package com.vedu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vedu.eduservice.entity.EduChapter;
import com.vedu.eduservice.entity.EduVideo;
import com.vedu.eduservice.entity.vo.ChapterVo;
import com.vedu.eduservice.entity.vo.VideoVo;
import com.vedu.eduservice.mapper.EduChapterMapper;
import com.vedu.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vedu.eduservice.service.EduVideoService;
import com.vedu.servicebase.exceptionhandler.EduException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author lixiande
 * @since 2020-10-24
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService videoService;

    @Override
    public List<ChapterVo> nestedList(String courseId) {
        System.out.println("==="+courseId);
        //最终要的到的数据列表
        ArrayList<ChapterVo> chapterVoArrayList = new ArrayList<>();

        //获取章节信息
        QueryWrapper<EduChapter> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("course_id", courseId);
        queryWrapper1.orderByAsc("sort", "id");
        List<EduChapter> chapters = baseMapper.selectList(queryWrapper1);

        System.out.println("9999"+chapters.toString());
        //获取课时信息
        QueryWrapper<EduVideo> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("course_id", courseId);
        queryWrapper2.orderByAsc("sort", "id");
        List<EduVideo> videos = videoService.list(queryWrapper2);

        System.out.println("+++++++"+videos.toString());
        //填充章节vo数据
        int count1 = chapters.size();
        System.out.println("-------------"+count1);
        for (int i = 0; i < count1; i++) {
            EduChapter chapter = chapters.get(i);

            //创建章节vo对象
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            chapterVoArrayList.add(chapterVo);

            //填充课时vo数据
            ArrayList<VideoVo> videoVoArrayList = new ArrayList<>();
            int count2 = videos.size();
            for (int j = 0; j < count2; j++) {

                EduVideo video = videos.get(j);
                System.out.println("=================="+video);
                if (chapter.getId().equals(video.getChapterId())) {

                    //创建课时vo对象
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    videoVoArrayList.add(videoVo);
                    System.out.println(videoVo);
                }
            }
            chapterVo.setChildren(videoVoArrayList);
        }

        return chapterVoArrayList;
    }

    @Override
    public boolean removeChapterById(String id) {
        if (videoService.getCountByChapterId(id)) {
            throw new EduException(20001, "该分章节下存在视频课程，请先删除视频课程");
        }

        Integer result = baseMapper.deleteById(id);
        return null != result && result > 0;
    }

    @Override
    public void removeChapterInfoById(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        boolean flag = this.remove(wrapper);
        if(!flag){
            throw new EduException(20001,"章节删除失败！");
        }
    }
}

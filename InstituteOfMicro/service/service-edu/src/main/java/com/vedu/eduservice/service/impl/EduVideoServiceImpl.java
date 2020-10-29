package com.vedu.eduservice.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vedu.eduservice.client.VodClient;
import com.vedu.eduservice.entity.EduVideo;
import com.vedu.eduservice.entity.vo.VideoInfoForm;
import com.vedu.eduservice.mapper.EduVideoMapper;
import com.vedu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vedu.servicebase.exceptionhandler.EduException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author lixiande
 * @since 2020-10-24
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    VodClient vodClient;

    @Override
    public boolean getCountByChapterId(String id) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",id);
        Integer count = baseMapper.selectCount(wrapper);
        return count > 0;
    }

    @Override
    public void saveInfo(VideoInfoForm videoInfoForm) {
        EduVideo eduVideo = new EduVideo();
        BeanUtils.copyProperties(videoInfoForm,eduVideo);
        boolean save = this.save(eduVideo);
        if(!save){
            throw new EduException(20001,"保存失败!");
        }
    }

    @Override
    public VideoInfoForm getInfoById(String id) {
        EduVideo eduVideo = this.getById(id);
        if(eduVideo == null){
            throw new EduException(20001,"该小节不存在！");
        }
        VideoInfoForm videoInfoForm = new VideoInfoForm();
        BeanUtils.copyProperties(eduVideo,videoInfoForm);
        return videoInfoForm;
    }

    @Override
    public void updateInfoById(VideoInfoForm videoInfoForm) {
        EduVideo eduVideo = new EduVideo();
        BeanUtils.copyProperties(videoInfoForm,eduVideo);
        boolean flag = this.updateById(eduVideo);
        if(!flag){
            throw new EduException(2001,"保存失败!");
        }
    }

    @Override
    public boolean removeInfoById(String id) {
        // TODO 删除视频资源
        EduVideo eduVideo = this.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId)){
            vodClient.removeVideo(videoSourceId);
            // 将视频id和名称置空
            EduVideo eduVideo1 = new EduVideo();
            eduVideo1.setVideoSourceId("");
            eduVideo1.setVideoOriginalName("");
            this.update(eduVideo1,null);
        }

        boolean flag = this.removeById(id);
        return flag;
    }

    @Override
    public void removeVideoById(String courseId) {
        //根据课程id查询所有视频列表
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.select("video_source_id");
        List<EduVideo> videoList = baseMapper.selectList(queryWrapper);

        //得到所有视频列表的云端原始视频id
        List<String> videoSourceIdList = new ArrayList<>();
        for (int i = 0; i < videoList.size(); i++) {
            EduVideo video = videoList.get(i);
            String videoSourceId = video.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)){
                videoSourceIdList.add(videoSourceId);
            }
        }

        //调用vod服务删除远程视频
        if(videoSourceIdList.size() > 0){
            vodClient.removeVideoList(videoSourceIdList);
        }

        QueryWrapper<EduVideo> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("course_id",courseId);
        boolean flag = this.remove(wrapper1);
        if(!flag){
            throw new EduException(20001,"小节删除失败！");
        }
    }

}

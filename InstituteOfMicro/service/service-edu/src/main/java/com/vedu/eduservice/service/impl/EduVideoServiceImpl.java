package com.vedu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vedu.eduservice.entity.EduVideo;
import com.vedu.eduservice.entity.vo.VideoInfoForm;
import com.vedu.eduservice.mapper.EduVideoMapper;
import com.vedu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vedu.servicebase.exceptionhandler.EduException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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

        boolean flag = this.removeById(id);
        return flag;
    }

    @Override
    public void removeVideoById(String courseId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        boolean flag = this.remove(wrapper);
        if(!flag){
            throw new EduException(20001,"小节删除失败！");
        }
    }


}

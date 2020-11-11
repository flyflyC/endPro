package com.vedu.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vedu.eduservice.entity.EduVideo;
import com.vedu.eduservice.entity.vo.VideoInfoForm;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author lixiande
 * @since 2020-10-24
 */
public interface EduVideoService extends IService<EduVideo> {

    boolean getCountByChapterId(String id);

    void saveInfo(VideoInfoForm videoInfoForm);

    VideoInfoForm getInfoById(String id);

    void updateInfoById(VideoInfoForm videoInfoForm);

    boolean removeInfoById(String id);

    void removeVideoById(String courseId);
}

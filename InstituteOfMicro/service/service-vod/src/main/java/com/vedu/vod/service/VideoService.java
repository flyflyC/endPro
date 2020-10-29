package com.vedu.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @program: InstituteOfMicro
 * @description:
 * @author: Mr.LiXianDe
 * @create: 2020-10-26 15:02
 **/
public interface VideoService {
    String uploadVideo(MultipartFile file);

    void removeVideo(String videoId);

    void removeVideoList(List<String> videoIdList);
}

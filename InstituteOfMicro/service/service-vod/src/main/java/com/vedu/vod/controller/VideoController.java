package com.vedu.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.vedu.common.Result;
import com.vedu.servicebase.exceptionhandler.EduException;
import com.vedu.vod.util.AliyunVodSDKUtils;
import com.vedu.vod.util.ConstantPropertiesUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(description="阿里云视频点播微服务")
@CrossOrigin //跨域
@RestController
@RequestMapping("/vod/video")
public class VideoController {

    @GetMapping("/getPlayAuth/{videoId}")
    public Result getVideoPlayAuth(@PathVariable("videoId") String videoId) {

        System.out.println("videoId============>"+videoId);
        try {
            //获取阿里云存储相关常量
            String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
            String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;

            //初始化
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(accessKeyId, accessKeySecret);

            //请求
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(videoId);

            //响应
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            //得到播放凭证
            String playAuth = response.getPlayAuth();


            //返回结果
            Map<String,Object> map = new HashMap<>();
            map.put("playAuth", playAuth);
            return Result.ok().data(map);

        } catch (Exception e) {
            throw new EduException(20001,"获取凭证失败");
        }
    }
}
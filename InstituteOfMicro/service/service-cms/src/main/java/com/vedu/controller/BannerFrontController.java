package com.vedu.controller;


import com.vedu.common.Result;
import com.vedu.entity.CrmBanner;
import com.vedu.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-10-19
 */
@RestController
@RequestMapping("/bannerfront")
@CrossOrigin
public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    @GetMapping("/getAllBanner")
    public Result getAllBanner(){
        List<CrmBanner> list = bannerService.selectAllBanner();
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",list);
        return Result.ok().data(map);
    }
}


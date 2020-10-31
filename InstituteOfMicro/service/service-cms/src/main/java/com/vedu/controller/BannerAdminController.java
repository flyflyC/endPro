package com.vedu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vedu.common.Result;
import com.vedu.entity.CrmBanner;
import com.vedu.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-10-19
 */
@RestController
@RequestMapping("/banneradmin")
@CrossOrigin
public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;

    @GetMapping("/pageBanner/{page}/{limit}")
    public Result pageBanner(@PathVariable long page
            , @PathVariable long limit){
        Page<CrmBanner> pageBanner = new Page<>(page,limit);
        bannerService.page(pageBanner, null);
        return Result.ok().data("items",pageBanner.getRecords())
                .data("total",pageBanner.getTotal());
    }

    @PostMapping("/addBanner")
    public Result addBanner(@RequestBody CrmBanner crmBanner){
        bannerService.save(crmBanner);
        return Result.ok();
    }

    @ApiOperation(value = "获取banner")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable String id){
        CrmBanner banner = bannerService.getById(id);
        return Result.ok().data("item",banner);
    }

    @ApiOperation(value = "修改banner")
    @PutMapping("/update")
    public Result updateById(@RequestBody CrmBanner banner){
        bannerService.updateById(banner);
        return Result.ok();
    }

    @ApiOperation(value = "删除banner")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id){
        bannerService.removeById(id);
        return Result.ok();
    }
}


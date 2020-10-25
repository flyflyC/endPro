package com.vedu.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: guli-parent
 * @description: 讲师条件查询实体类
 * @author: Mr.Wang
 * @create: 2020-10-16 15:28
 **/
@ApiModel(value = "Teacher查询对象", description = "讲师查询对象封装")
@Data
public class TeacherQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "教师名称，模糊查询")
    private String name;
    @ApiModelProperty(value = "头衔：1 高级讲师 2 首席讲师")
    private Integer level;
    @ApiModelProperty(value = "查询开始时间",example = "2019-01-01 10:10:10")
    private String begin;
    @ApiModelProperty(value = "查询结束时间",example = "2019-12-01 10:10:10")
    private String end;
}

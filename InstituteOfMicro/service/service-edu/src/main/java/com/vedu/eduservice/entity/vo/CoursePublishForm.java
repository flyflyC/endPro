package com.vedu.eduservice.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.LiXianDe
 * @create: 2020-10-25 11:20
 **/
@Data
public class CoursePublishForm implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}

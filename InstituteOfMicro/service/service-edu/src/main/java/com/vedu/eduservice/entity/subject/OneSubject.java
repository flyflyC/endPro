package com.vedu.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: guli-parent
 * @description: 课程一级分类
 * @author: Mr.LiXianDe
 * @create: 2020-10-23 21:21
 **/
@Data
public class OneSubject {
    private String id;
    private String title;
    private List<TwoSubject> children = new ArrayList<>();
}

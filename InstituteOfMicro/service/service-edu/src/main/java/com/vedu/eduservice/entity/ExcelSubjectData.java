package com.vedu.eduservice.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2020-10-23 17:07
 **/
@Data
public class ExcelSubjectData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}

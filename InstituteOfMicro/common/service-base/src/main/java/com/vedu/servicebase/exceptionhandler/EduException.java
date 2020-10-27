package com.vedu.servicebase.exceptionhandler;


import com.vedu.commonutils.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2020-10-16 19:27
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EduException extends RuntimeException {
    private Integer code;
    private String msg;

    public EduException(ResultCode error) {
        this.code = error.getCode();
    }
}

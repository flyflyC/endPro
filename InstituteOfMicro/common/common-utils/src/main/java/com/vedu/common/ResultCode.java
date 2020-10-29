package com.vedu.common;

/**
 * @program: guli-parent
 * @description:结果状态码 成功20000 失败20001
 * @author: Mr.Wang
 * @create: 2020-10-16 10:47
 **/
public enum ResultCode {
    SUCCESS(20000), // 成功
    ERROR(20001); // 失败

    private final Integer code;

    private ResultCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}

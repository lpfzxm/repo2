package com.dhcens.emviewdoctor.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

    SQLRESULT_INVALID_PARAM(400,"SQL结果集为空"),
    INVALID_PARAM(400, "入参数据异常"),
    ;

    private int code;
    private String msg;

    public String getMsg()
    {
        return this.msg;
    }
    public int getCode() {
        return this.code;
    }

}

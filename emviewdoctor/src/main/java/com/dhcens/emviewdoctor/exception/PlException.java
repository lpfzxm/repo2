package com.dhcens.emviewdoctor.exception;

public class PlException extends RuntimeException {
    /**
     * 错误代码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String msg;

    public PlException(ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMsg();
    }


}

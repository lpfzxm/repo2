package com.dhcens.emviewdoctor.exception;

public class LyException extends RuntimeException {
    private int code;
    private String message;

    public LyException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public LyException(ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMsg();
    }

}

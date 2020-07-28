package com.dhcens.emviewdoctor.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 拦截业务异常
     *
     * @param e BizException
     * @return ResultCommon
     */
    @ResponseBody
    @ExceptionHandler(LyException.class)
    public String bizException(LyException e) {
        log.error("业务异常:" + e.getMessage());
        return "系统异常:" + e.getMessage();
    }


    /**
     * 拦截系统异常
     *
     * @param e Exception
     * @return ResultCommon
     */
    @ExceptionHandler(RuntimeException.class)
    public String exceptionHandler(RuntimeException e) {
        log.error("系统异常:" + e.getMessage());
        log.error(Arrays.toString(e.getStackTrace()));
        return "系统异常:" + e.getMessage();
    }



}

package com.dhcens.emviewdoctor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@MapperScan("com.dhcens.emviewdoctor.mapper")
@Slf4j
@EnableCaching
@EnableAsync
public class EmviewdoctorApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmviewdoctorApplication.class, args);
        log.info("=========================启动成功========================");
    }


}

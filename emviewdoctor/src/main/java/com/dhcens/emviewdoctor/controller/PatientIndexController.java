package com.dhcens.emviewdoctor.controller;


import com.dhcens.emviewdoctor.entity.VO.PatientIndexVO;
import com.dhcens.emviewdoctor.service.PatientIndexService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 获取根据条件查询患者索引信息
 *
 * @author : zhibao
 */
@Api(tags = "查询患者就诊记录")
@RestController
@RequestMapping("/hdc/SerachQuery")
public class PatientIndexController {

    @Resource
    PatientIndexService patientIndexService;

    @ApiOperation(value = "查询患者就诊记录")
    @PostMapping(value = "select-all")
    public List<PatientIndexVO> selectAll(
            @ApiParam(value = "页码")
            @RequestParam(name = "page", required = false) String page,
            @ApiParam(value = "行数")
            @RequestParam(name = "rows", required = false) String rows,
            @ApiParam(value = "患者his登记号")
            @RequestParam(name = "hosPatientId", required = false) String hosPatientId,
            @ApiParam(value = "性别代码")
            @RequestParam(name = "sexCode", required = false) String sexCode
    ) {
        return patientIndexService.selectAll(hosPatientId, sexCode, page, rows);
    }

}

package com.dhcens.emviewdoctor.controller;

import com.dhcens.emviewdoctor.entity.RT.DataRT;
import com.dhcens.emviewdoctor.entity.VO.PaallergyVO;
import com.dhcens.emviewdoctor.entity.result.OutputResult;
import com.dhcens.emviewdoctor.service.PaallergyService;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "查询过敏信息")
@RequestMapping("/hdc/SerachQuery")
public class PaallergyController {

    @Autowired
    private PaallergyService paallergyService;

    @ApiOperation(value = "查询过敏信息", notes = "通过条件查询")
    @PostMapping("MES0011")
    public OutputResult selectPaallergy(DataRT data){
        PageInfo<PaallergyVO> pageInfo = paallergyService.selectPaallergy(data.getParams(), data.getPage(), data.getRows());
        return OutputResult.toJson(pageInfo);
    }
    }


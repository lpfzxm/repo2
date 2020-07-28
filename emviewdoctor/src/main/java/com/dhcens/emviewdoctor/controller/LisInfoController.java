package com.dhcens.emviewdoctor.controller;

import com.dhcens.emviewdoctor.entity.RT.DataRT;
import com.dhcens.emviewdoctor.entity.VO.LisItemresultVO;
import com.dhcens.emviewdoctor.entity.result.OutputResult;
import com.dhcens.emviewdoctor.service.LisinfoService;
import com.github.pagehelper.PageInfo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "查询检验相关信息")
@RequestMapping("/hdc/SerachQuery")
public class LisInfoController {
    @Resource
   private LisinfoService lisinfoService;
    @ApiOperation(value = "查询检验对比信息", notes = "通过条件查询")
    @PostMapping("MES0007")
    public OutputResult selectLisItemResult(DataRT data) throws Exception {
        PageInfo<LisItemresultVO> pageInfo = lisinfoService.selectLisItemResult(data.getParams(), data.getPage(), data.getRows());
        return OutputResult.toJson(pageInfo);
    }


}

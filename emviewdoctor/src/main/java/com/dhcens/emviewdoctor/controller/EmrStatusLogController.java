package com.dhcens.emviewdoctor.controller;

import com.dhcens.emviewdoctor.entity.RT.DataRT;
import com.dhcens.emviewdoctor.entity.VO.EmrStatusLogVO;
import com.dhcens.emviewdoctor.entity.result.OutputResult;
import com.dhcens.emviewdoctor.service.EmrStatusLogService;
import com.github.pagehelper.PageInfo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author lipengfei <184441376@qq.com><br/>
 * @date 2020/4/10
 */
@RestController
@Api(tags = "查询病案信息")
@RequestMapping("/hdc/SerachQuery")
public class EmrStatusLogController {
    @Resource
    private EmrStatusLogService emrStatusLogService;

    @ApiOperation(value = "查询病案信息", notes = "通过条件查询")
    @PostMapping("MES0016")
    public OutputResult selectEmrStatusLogByCondiction(DataRT data) {
        PageInfo<EmrStatusLogVO> pageInfo= emrStatusLogService.selectEmrStatuLog(data.getParams(), data.getPage(), data.getRows());
        return OutputResult.toJson(pageInfo);

    }
}

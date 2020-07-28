package com.dhcens.emviewdoctor.controller;

import com.dhcens.emviewdoctor.entity.RT.DataRT;
import com.dhcens.emviewdoctor.entity.VO.ObservationsVO;
import com.dhcens.emviewdoctor.entity.result.OutputResult;
import com.dhcens.emviewdoctor.service.ObserService;
import com.github.pagehelper.PageInfo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author lipengfei <lipengfei_ylrj@dhcc.com><br/>
 * @date 2020/1/9
 */
@RestController
@Api(tags = "查询生命体征信息")
@RequestMapping("/hdc/SerachQuery")
public class ObserController {

   @Resource
    private ObserService obserService;
    @ApiOperation(value = "查询生命体征信息", notes = "通过条件查询")
    @PostMapping("MES0014")
    public OutputResult selectObservations(DataRT data) throws Exception {
        PageInfo<ObservationsVO> pageInfo = obserService.selectObservations(data.getParams(), data.getPage(), data.getRows());
        return OutputResult.toJson(pageInfo);
    }

}

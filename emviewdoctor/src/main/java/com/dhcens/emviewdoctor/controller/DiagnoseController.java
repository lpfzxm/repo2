package com.dhcens.emviewdoctor.controller;

import com.dhcens.emviewdoctor.entity.RT.DataRT;
import com.dhcens.emviewdoctor.entity.VO.DiagnoseVO;
import com.dhcens.emviewdoctor.entity.result.OutputResult;
import com.dhcens.emviewdoctor.service.DiagnoseService;
import com.github.pagehelper.PageInfo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName DiagnoseController
 * @Description: TODO
 * @Author lipengfei
 * @Date 2019/12/27
 * @Version V1.0
 **/
@RestController
@Api(tags = "查询诊断信息")
@RequestMapping("/hdc/SerachQuery")
public class DiagnoseController {

    @Resource
    private DiagnoseService diagnoseService;

    @ApiOperation(value = "查询诊断信息", notes = "通过条件查询")
    @PostMapping("MES0004")
    public OutputResult selectDiagnose(DataRT data)  {
        PageInfo<DiagnoseVO> pageInfo = diagnoseService.selectDiagnose(data.getParams(), data.getPage(), data.getRows());
        return OutputResult.toJson(pageInfo);
    }
}

package com.dhcens.emviewdoctor.controller;


import com.dhcens.emviewdoctor.entity.RT.DataRT;
import com.dhcens.emviewdoctor.entity.VO.RISOrderInfoVO;
import com.dhcens.emviewdoctor.entity.VO.RisCountVO;
import com.dhcens.emviewdoctor.entity.result.OutputResult;
import com.dhcens.emviewdoctor.service.RISOrderService;
import com.github.pagehelper.PageInfo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * @ClassName RISOrderController
 * @Description: TODO
 * @Author lipengfei
 * @Date 2019/12/27
 * @Version V1.0
 **/

@RestController
@Api(tags = "查询检查信息信息")
@RequestMapping("/hdc/SerachQuery")
public class RisOrderController {
    @Resource
    private RISOrderService risOrderService;

    @ApiOperation(value = "查询检查信息", notes = "通过条件查询")
    @PostMapping("MES0012")
    public OutputResult selectRISOrder(DataRT data) {
        PageInfo<RISOrderInfoVO> pageInfo = risOrderService.selectRISOrder(data.getParams(), data.getPage(), data.getRows());
        return OutputResult.toJson(pageInfo);
    }

    @ApiOperation(value = "查询检查报告数量分类", notes = "通过条件查询")
    @PostMapping("MES0013")
    public OutputResult SelectRisReportCount(DataRT data){
        PageInfo<RisCountVO> pageInfo=risOrderService.selectRISCount(data.getParams(), data.getPage(), data.getRows());
        return OutputResult.toJson(pageInfo);
    }


}

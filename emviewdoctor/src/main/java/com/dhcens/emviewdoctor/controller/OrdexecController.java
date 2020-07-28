package com.dhcens.emviewdoctor.controller;

import com.dhcens.emviewdoctor.entity.RT.DataRT;
import com.dhcens.emviewdoctor.entity.VO.OrdexecVO;
import com.dhcens.emviewdoctor.entity.result.OutputResult;
import com.dhcens.emviewdoctor.service.OrdexecService;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author lipengfei <184441376@qq.com><br/>
 * @date 2020/1/17
 */
@RestController
@Api(tags = "查询医嘱执行信息")
@RequestMapping("/hdc/SerachQuery")
public class OrdexecController {
    @Autowired
    private OrdexecService ordexecService;

    @ApiOperation(value = "询医嘱执行信息", notes = "通过条件查询")
    @PostMapping("MES0010")
    public OutputResult selectOrdexec(DataRT data){
        PageInfo<OrdexecVO> pageInfo = ordexecService.selectOrdexec(data.getParams(), data.getPage(), data.getRows());
        return OutputResult.toJson(pageInfo);
    }

}

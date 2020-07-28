package com.dhcens.emviewdoctor.controller;

import com.dhcens.emviewdoctor.entity.RT.DataRT;
import com.dhcens.emviewdoctor.entity.VO.DrugDetailVO;
import com.dhcens.emviewdoctor.entity.VO.DrugDictVO;
import com.dhcens.emviewdoctor.entity.result.OutputResult;
import com.dhcens.emviewdoctor.service.DictService;
import com.dhcens.emviewdoctor.service.DrugDetailService;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName DrugDetailController
 * @Description: TODO
 * @Author lipengfei
 * @Date 2019/12/17
 * @Version V1.0
 **/
@RestController
@Api(tags = "查询用药清单信息")
@RequestMapping("/hdc/SerachQuery")
public class DrugDetailController {
    @Resource
    private DrugDetailService drugDetailService;

    @Autowired
    private DictService dictService;

    @ApiOperation(value = "查询用药清单信息", notes = "通过条件查询")
    @PostMapping("MES0005")
    public OutputResult selectDrugList(DataRT data)  {
        PageInfo<DrugDetailVO> pageInfo = drugDetailService.selectDrugList(data.getParams(), data.getPage(), data.getRows());
        return OutputResult.toJson(pageInfo);
    }

    @ApiOperation(value = "查询用药字典信息", notes = "通过条件查询")
    @PostMapping("MES0006")
    public OutputResult selcetDrugDict(DataRT data){
        PageInfo<DrugDictVO> pageInfo =dictService.selectDrugDict(data.getParams(), data.getPage(), data.getRows());
        return OutputResult.toJson(pageInfo);
    }
}

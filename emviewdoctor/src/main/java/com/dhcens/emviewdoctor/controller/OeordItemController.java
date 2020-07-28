package com.dhcens.emviewdoctor.controller;

import com.dhcens.emviewdoctor.entity.RT.DataRT;
import com.dhcens.emviewdoctor.entity.VO.DrugDictVO;
import com.dhcens.emviewdoctor.entity.VO.OeordItemVO;
import com.dhcens.emviewdoctor.entity.result.OutputResult;
import com.dhcens.emviewdoctor.service.DictService;
import com.dhcens.emviewdoctor.service.OeordItemService;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName DrugDetailController
 * @Description: 获取医嘱信息
 * @Author zhibao
 * @Date 2019/12/17
 * @Version V1.0
 **/
@RestController
@Api(tags = "查询医嘱信息")
@RequestMapping("/hdc/SerachQuery")
public class OeordItemController {

    @Autowired
    OeordItemService oeordItemService;
    @Autowired
    private DictService dictService;


    @ApiOperation(value = "查询医嘱信息", notes = "通过条件查询")
    @PostMapping(value = "MES0008")
    public OutputResult selectDrugList(DataRT data) {
        PageInfo<OeordItemVO> pageInfo = oeordItemService.selectOeordItemByCondiction(data.getParams(), data.getPage(), data.getRows());
        return OutputResult.toJson(pageInfo);
    }


    @ApiOperation(value = "查询医嘱字典信息", notes = "通过条件查询")
    @PostMapping("MES0009")
    public OutputResult selcetOEordDict(DataRT data) {
        PageInfo<DrugDictVO> pageInfo = dictService.selectOEordDict(data.getParams(), data.getPage(), data.getRows());
        return OutputResult.toJson(pageInfo);
    }
}

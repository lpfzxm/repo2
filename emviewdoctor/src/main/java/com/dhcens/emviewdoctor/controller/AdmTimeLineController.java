package com.dhcens.emviewdoctor.controller;

import com.dhcens.emviewdoctor.entity.RT.DataRT;
import com.dhcens.emviewdoctor.entity.VO.AdmDictVO;
import com.dhcens.emviewdoctor.entity.VO.TimeLineAdmVO;
import com.dhcens.emviewdoctor.entity.result.OutputResult;
import com.dhcens.emviewdoctor.service.DictService;
import com.dhcens.emviewdoctor.service.TimeLineService;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName TimeLineAdmController
 * @Description: TODO
 * @Author lipengfei
 * @Date 2019/12/26
 * @Version V1.0
 **/

@RestController
@Api(tags = "根据时间轴获取诊断信息")
@RequestMapping("/hdc/SerachQuery")
public class AdmTimeLineController {

    @Autowired
    private TimeLineService timeLineService;

    @Autowired
    private DictService dictService;

    @ApiOperation(value = "查询诊断时间轴信息", notes = "通过条件查询")
    @PostMapping("MES0002")
    public OutputResult selectTimeLineByCondiction(DataRT data) {
        PageInfo<TimeLineAdmVO> timeLineAdmVOPageInfo= timeLineService.selectTimeLineByCondiction(data.getParams(), data.getPage(), data.getRows());
        return OutputResult.toJson(timeLineAdmVOPageInfo);

    }


    @ApiOperation(value = "查询就诊字典信息", notes = "通过条件查询")
    @PostMapping("MES0003")
  public OutputResult selectAdmDictByCondiction(DataRT data) {
        PageInfo<AdmDictVO> admDictVOPageInfo=dictService.selectAdmDict(data.getParams(), data.getPage(), data.getRows());
        return OutputResult.toJson(admDictVOPageInfo);
    }
}

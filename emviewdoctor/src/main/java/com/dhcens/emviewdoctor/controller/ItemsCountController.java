package com.dhcens.emviewdoctor.controller;

import com.dhcens.emviewdoctor.entity.RT.DataRT;
import com.dhcens.emviewdoctor.entity.VO.CountItemsVO;
import com.dhcens.emviewdoctor.entity.result.OutputResult;
import com.dhcens.emviewdoctor.service.QueryItemCountService;
import com.github.pagehelper.PageInfo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 查询各个分支项目的总数
 * @author zhibao
 */
@RestController
@Api(tags = "异步查询分类项目的数量统计")
@RequestMapping("/hdc/SerachQuery")
@Slf4j
public class ItemsCountController {

   @Resource
   private QueryItemCountService countItemsService;


    @ApiOperation(value = "异步测试", notes = "通过条件查询")
    @PostMapping("MES0015")
    public OutputResult asyncTest(DataRT data) throws Exception{
        CompletableFuture<CountItemsVO> r1 = countItemsService.queryOrdItem(data.getParams());
        CompletableFuture<CountItemsVO> r2 = countItemsService.queryRisRegister(data.getParams());
        CompletableFuture<CountItemsVO> r3 = countItemsService.queryLisReport(data.getParams());
        CompletableFuture<CountItemsVO> r4 = countItemsService.queryMedicalDetial(data.getParams());
        CompletableFuture<CountItemsVO> r5 = countItemsService.queryPatDocument(data.getParams());
        CompletableFuture<CountItemsVO> r6 = countItemsService.queryDiagnose(data.getParams());
        CountItemsVO countItemsVo1 = r1.get();
        CountItemsVO countItemsVo2 = r2.get();
        CountItemsVO countItemsVo3 = r3.get();
        CountItemsVO countItemsVo4 = r4.get();
        CountItemsVO countItemsVo5 = r5.get();
        CountItemsVO countItemsVo6 = r6.get();
        List<CountItemsVO> countItemsVoOut = new ArrayList<CountItemsVO>();
        countItemsVoOut.add(countItemsVo1);
        countItemsVoOut.add(countItemsVo2);
        countItemsVoOut.add(countItemsVo3);
        countItemsVoOut.add(countItemsVo4);
        countItemsVoOut.add(countItemsVo5);
        countItemsVoOut.add(countItemsVo6);
        PageInfo<CountItemsVO> countItemsVOPageInfo = new PageInfo<>(countItemsVoOut);
        return OutputResult.toJson(countItemsVOPageInfo);
    }

}

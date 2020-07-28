package com.dhcens.emviewdoctor.service.impl;

import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.CountItemsVO;
import com.dhcens.emviewdoctor.mapper.CountItemsMapper;
import com.dhcens.emviewdoctor.service.QueryItemCountService;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.annotation.Resource;

@Service
public class QueryItemCountServiceImpl implements QueryItemCountService {

    @Resource
    private CountItemsMapper countItemsMapper;

    @Override
    public PageInfo<CountItemsVO> countItemsTotal(String parameter) throws Exception {
        return null;
    }

    @Override
    public CompletableFuture<CountItemsVO> queryOrdItem(Params paramsobject) throws InterruptedException {
        List<Integer> countlist = countItemsMapper.selectOeordItemcount(paramsobject);
        CountItemsVO countItemsVo = new CountItemsVO();
        countItemsVo.setTotalnum(countlist.get(0));
        countItemsVo.setType("OE_OrdItem");
        return CompletableFuture.completedFuture(countItemsVo);
    }

    @Override
    public CompletableFuture<CountItemsVO> queryRisRegister(Params paramsobject) throws InterruptedException {
        List<Integer> countlist = countItemsMapper.selectRisReportcount(paramsobject);
        CountItemsVO countItemsVo = new CountItemsVO();
        countItemsVo.setTotalnum(countlist.get(0));
        countItemsVo.setType("Ris_Report");
        return CompletableFuture.completedFuture(countItemsVo);
    }

    @Override
    public CompletableFuture<CountItemsVO> queryLisReport(Params paramsobject) throws InterruptedException {
        List<Integer> countlist = countItemsMapper.selectLisReportcount(paramsobject);
        CountItemsVO countItemsVo = new CountItemsVO();
        countItemsVo.setTotalnum(countlist.get(0));
        countItemsVo.setType("Lis_Report");
        return CompletableFuture.completedFuture(countItemsVo);
    }

    @Override
    public CompletableFuture<CountItemsVO> queryMedicalDetial(Params paramsobject) throws InterruptedException {
        List<Integer> countlist = countItemsMapper.selectMedicalDetialcount(paramsobject);
        CountItemsVO countItemsVo = new CountItemsVO();
        countItemsVo.setTotalnum(countlist.get(0));
        countItemsVo.setType("Medical_list");
        return CompletableFuture.completedFuture(countItemsVo);
    }

    @Override
    public CompletableFuture<CountItemsVO> queryPatDocument(Params paramsobject) throws InterruptedException {
        return null;
    }

    @Override
    public CompletableFuture<CountItemsVO> queryDiagnose(Params paramsobject) throws InterruptedException {
        List<Integer> countlist = countItemsMapper.selectDiagnosecount(paramsobject);
        CountItemsVO countItemsVo = new CountItemsVO();
        countItemsVo.setTotalnum(countlist.get(0));
        countItemsVo.setType("PA_Diagnose");
        return CompletableFuture.completedFuture(countItemsVo);
    }
}

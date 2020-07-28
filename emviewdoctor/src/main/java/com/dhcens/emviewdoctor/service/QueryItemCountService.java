package com.dhcens.emviewdoctor.service;

import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.CountItemsVO;
import com.github.pagehelper.PageInfo;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;


public interface QueryItemCountService {

    /**
     * 获取详细项目的item数量
     * @param parameter 入参条件
     * @return 详细项目的数量
     */

    public PageInfo<CountItemsVO> countItemsTotal(String parameter) throws Exception;

    @Async
    public CompletableFuture<CountItemsVO> queryOrdItem(Params paramsobject) throws  InterruptedException;
    @Async
    public CompletableFuture<CountItemsVO> queryRisRegister(Params paramsobject) throws  InterruptedException;
    @Async
    public CompletableFuture<CountItemsVO> queryLisReport(Params paramsobject) throws InterruptedException;
    @Async
    public CompletableFuture<CountItemsVO> queryMedicalDetial(Params paramsobject) throws InterruptedException;
    @Async
    public CompletableFuture<CountItemsVO> queryPatDocument(Params paramsobject) throws InterruptedException;
    @Async
    public CompletableFuture<CountItemsVO> queryDiagnose(Params paramsobject) throws InterruptedException;







}

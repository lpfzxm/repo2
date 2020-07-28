package com.dhcens.emviewdoctor.entity.result;

import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 *
 * @author zhibao
 */
@Data
public class OutputResult {
    private List data = new ArrayList(); //结果集
    private int draw;  //当前页数
    private long recordsFiltered;
    private long recordsTotal;
    private int pages;  //总页数
    private int pageSize;//每页的数量

    public static OutputResult toJson(PageInfo pageInfo){
        OutputResult outputResult = new OutputResult();
        List list = pageInfo.getList();
        outputResult.setData(list);
        outputResult.setDraw(pageInfo.getPageNum());
        outputResult.setRecordsTotal(pageInfo.getTotal());
        outputResult.setPages(pageInfo.getPages());
        outputResult.setRecordsFiltered(pageInfo.getTotal());
        outputResult.setPageSize(pageInfo.getPageSize());
        return outputResult;
    }


}

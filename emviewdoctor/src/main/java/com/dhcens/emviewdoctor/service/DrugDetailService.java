package com.dhcens.emviewdoctor.service;

import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.DrugDetailVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DrugDetailService {
    PageInfo<DrugDetailVO> selectDrugList(Params paramsobject, Integer pageNum, Integer pageSize);

    List<DrugDetailVO> getAllDrugDateilData(List<DrugDetailVO> drugDetailVOS);


}

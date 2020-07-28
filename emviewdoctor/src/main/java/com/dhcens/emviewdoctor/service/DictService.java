package com.dhcens.emviewdoctor.service;

import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.AdmDictVO;
import com.dhcens.emviewdoctor.entity.VO.DrugDictVO;
import com.github.pagehelper.PageInfo;

public interface DictService {
    //用药清单字典表
    PageInfo<DrugDictVO> selectDrugDict(Params paramsobject, Integer pageNum, Integer pageSize);

    //医嘱字典表
    PageInfo<DrugDictVO> selectOEordDict(Params paramsobject, Integer pageNum, Integer pageSize);


    //医嘱字典表
    PageInfo<AdmDictVO> selectAdmDict(Params paramsobject, Integer pageNum, Integer pageSize);
}

package com.dhcens.emviewdoctor.service;

import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.PaallergyVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PaallergyService {
    PageInfo<PaallergyVO> selectPaallergy(Params paramsobject, Integer pageNum, Integer pageSize);

    List<PaallergyVO> getAllPaallergyData(List<PaallergyVO> paallergyVOS);
}

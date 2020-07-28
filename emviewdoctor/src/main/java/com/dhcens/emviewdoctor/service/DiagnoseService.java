package com.dhcens.emviewdoctor.service;


import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.DiagnoseVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DiagnoseService {
    PageInfo<DiagnoseVO> selectDiagnose(Params paramsobject, Integer pageNum, Integer pageSize);

    List<DiagnoseVO> getAllDiagnoseData(List<DiagnoseVO> diagnoseVOS);
}

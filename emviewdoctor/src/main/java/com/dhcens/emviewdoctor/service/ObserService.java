package com.dhcens.emviewdoctor.service;

import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.ObservationsVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author lipengfei <lipengfei_ylrj@dhcc.com><br/>
 * @date 2020/1/9
 */
public interface ObserService {
    PageInfo<ObservationsVO> selectObservations(Params paramsobject, Integer pageNum, Integer pageSize);

    List<ObservationsVO> getAllObservationsData(List<ObservationsVO> observationsVOS);
}


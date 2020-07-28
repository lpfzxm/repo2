package com.dhcens.emviewdoctor.mapper;

import com.dhcens.emviewdoctor.entity.RT.ObserInfoRT;
import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.ObservationsVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lipengfei <lipengfei_ylrj@dhcc.com><br/>
 * @date 2020/1/9
 */
public interface ObservationsMapper {
    List<ObservationsVO> selectObservations(@Param("paramsobject") Params paramsobject, ObserInfoRT obserInfo);
}


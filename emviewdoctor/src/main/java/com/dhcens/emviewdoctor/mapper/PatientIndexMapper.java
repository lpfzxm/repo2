package com.dhcens.emviewdoctor.mapper;


import com.dhcens.emviewdoctor.entity.VO.PatientIndexVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @author : zhibao
 */
public interface PatientIndexMapper {

    List<PatientIndexVO> selectAll(
            @Param("hosPatientId") String hosPatientId,
            @Param("sexCode") String sexCode,
            @Param("page") int page,
            @Param("rows") int rows
    );

   String selectPatpatientId(String HosPatientID);
}

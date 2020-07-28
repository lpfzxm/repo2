package com.dhcens.emviewdoctor.mapper;


import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.PatientInfoVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PatientInfoMapper {

    /**
     * 查询患者基本信息
     * @param paramsobject  mybatis入参对象
     * @return PatientInfoVO集合
     */
    List<PatientInfoVO> selectPatientInfo(@Param("paramsobject") Params paramsobject);

}

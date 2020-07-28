package com.dhcens.emviewdoctor.mapper;

import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.AdmDictVO;
import com.dhcens.emviewdoctor.entity.VO.DrugDictVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DictMapper {
    List<DrugDictVO> selectDrugDictByCondition(@Param("paramsobject") Params paramsobject);

    List<DrugDictVO> selectOEordDictByCondition(@Param("paramsobject") Params paramsobject);

    List<AdmDictVO> selectAdmDictByCondition(@Param("paramsobject") Params paramsobject);
}

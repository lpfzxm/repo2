package com.dhcens.emviewdoctor.mapper;

import com.dhcens.emviewdoctor.entity.RT.OEORIItemInfoRT;
import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.DrugDetailVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DrugDetailMapper {

    List<DrugDetailVO> selectDrugDetailByCondition(@Param("paramsobject") Params paramsobject, @Param("oeoriItemInfo") OEORIItemInfoRT oeoriItemInfo);

}


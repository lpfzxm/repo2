package com.dhcens.emviewdoctor.mapper;

import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.PaallergyVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaallergyMapper {
    List<PaallergyVO> selectPaallergy(@Param("paramsobject") Params paramsobject);
}

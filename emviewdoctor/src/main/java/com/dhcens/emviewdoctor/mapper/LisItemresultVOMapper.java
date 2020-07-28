package com.dhcens.emviewdoctor.mapper;


import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.LisItemresultVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LisItemresultVOMapper {

    List<LisItemresultVO> selectLisItemresult(@Param("paramsobject") Params paramsobject);



}
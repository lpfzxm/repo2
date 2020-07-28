package com.dhcens.emviewdoctor.mapper;

import com.dhcens.emviewdoctor.entity.RT.PAAdmInfoRT;
import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.TimeLineAdmVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhibao
 */
public interface TimeLineAdmMapper {


    List<TimeLineAdmVO> selectTimeLineAdm(@Param("paramsobject") Params paramsobject, @Param("paAdmInfo") PAAdmInfoRT paAdmInfo);



}

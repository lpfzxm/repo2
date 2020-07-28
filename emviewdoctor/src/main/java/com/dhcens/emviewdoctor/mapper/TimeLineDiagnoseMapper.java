package com.dhcens.emviewdoctor.mapper;

import com.dhcens.emviewdoctor.entity.VO.TimeLineDiagnoseVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhibao
 */
public interface TimeLineDiagnoseMapper {

    List<TimeLineDiagnoseVO> selectTimeLineDiagnose(@Param("PatVisitNumberList") List<String> PatVisitNumberList);

}

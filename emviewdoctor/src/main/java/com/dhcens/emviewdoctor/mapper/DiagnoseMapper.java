package com.dhcens.emviewdoctor.mapper;


import com.dhcens.emviewdoctor.entity.RT.PADDiagInfoRT;
import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.DiagnoseVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName DiagnoseMapper
 * @Description: TODO
 * @Author lipengfei
 * @Date 2019/12/27
 * @Version V1.0
 **/

public interface DiagnoseMapper {
    List<DiagnoseVO> selectDiagnoseByCondition(@Param("paramsobject") Params paramsobject, @Param("padDiagInfo") PADDiagInfoRT padDiagInfo);
}

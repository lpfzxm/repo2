package com.dhcens.emviewdoctor.mapper;

import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.RISOrderInfoVO;
import com.dhcens.emviewdoctor.entity.VO.RisCountVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName RISOrderMapper
 * @Description: TODO
 * @Author lipengfei
 * @Date 2019/12/27
 * @Version V1.0
 **/

public interface RISOrderMapper {

    List<RISOrderInfoVO> selectRISOrderByCondition(@Param("paramsobject") Params paramsobject);

    List<RisCountVO> selectRISCountByCondition(@Param("paramsobject") Params paramsobject);

    List<RISOrderInfoVO> selectRISReportByCondition(@Param("risrexamidList") List<String> risrexamid);

}

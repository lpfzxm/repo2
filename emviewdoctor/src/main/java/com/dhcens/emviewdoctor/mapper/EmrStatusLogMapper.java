package com.dhcens.emviewdoctor.mapper;


import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.EmrStatusLogVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lipengfei <184441376@qq.com><br/>
 * @date 2020/4/9
 */
public interface EmrStatusLogMapper {
    List<EmrStatusLogVO> selectDEmrStatusLogByCondition(@Param("paramsobject") Params paramsobject);

}


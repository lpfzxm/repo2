package com.dhcens.emviewdoctor.mapper;


import com.dhcens.emviewdoctor.entity.RT.OEORIItemInfoRT;
import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.OeordItemVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OeordItemlMapper {
    /**
     *
     * @param paramsobject
     * @param oeoriItemInfo
     * @return
     */
    List<OeordItemVO> selectOeordItemByCondition(@Param("paramsobject") Params paramsobject, @Param("oeoriItemInfo") OEORIItemInfoRT oeoriItemInfo);

}

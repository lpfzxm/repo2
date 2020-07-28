package com.dhcens.emviewdoctor.mapper;

import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.OrdexecVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author lipengfei <184441376@qq.com><br/>
 * @date 2020/1/17
 */
public interface OrdexecMapper {
    List<OrdexecVO> selectOrdexecByCondition(@Param("paramsobject") Params paramsobject);
}


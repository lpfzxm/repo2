package com.dhcens.emviewdoctor.service;


import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.OrdexecVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author lipengfei <184441376@qq.com><br/>
 * @date 2020/1/17
 */
public interface OrdexecService {
    PageInfo<OrdexecVO> selectOrdexec(Params paramsobject, Integer pageNum, Integer pageSize);

    List<OrdexecVO> getAllOrdexecVOData(List<OrdexecVO> ordexecVOS);
}


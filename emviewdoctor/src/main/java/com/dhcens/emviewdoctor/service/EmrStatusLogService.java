package com.dhcens.emviewdoctor.service;

import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.EmrStatusLogVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author lipengfei <184441376@qq.com><br/>
 * @date 2020/4/10
 */
public interface EmrStatusLogService {
    PageInfo<EmrStatusLogVO> selectEmrStatuLog(Params paramsobject, Integer pageNum, Integer pageSize);

    List<EmrStatusLogVO> getAllEmrStatuLogData(List<EmrStatusLogVO> emrStatusLogVOS);

}


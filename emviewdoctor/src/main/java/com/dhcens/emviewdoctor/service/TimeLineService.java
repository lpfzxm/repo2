package com.dhcens.emviewdoctor.service;

import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.TimeLineAdmVO;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Component;

@Component
public interface TimeLineService {

    PageInfo<TimeLineAdmVO> selectTimeLineByCondiction(Params paramsobject, Integer pageNum, Integer pageSize);

}

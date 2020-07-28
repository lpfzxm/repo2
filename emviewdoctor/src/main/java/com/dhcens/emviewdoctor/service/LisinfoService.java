package com.dhcens.emviewdoctor.service;


import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.LisItemresultVO;
import com.github.pagehelper.PageInfo;

public interface LisinfoService {


     PageInfo<LisItemresultVO> selectLisItemResult(Params paramsobject, Integer pageNum, Integer pageSize);





}

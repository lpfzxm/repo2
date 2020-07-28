package com.dhcens.emviewdoctor.service;

import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.RISOrderInfoVO;
import com.dhcens.emviewdoctor.entity.VO.RisCountVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RISOrderService {

    PageInfo<RISOrderInfoVO> selectRISOrder(Params paramsobject, Integer pageNum, Integer pageSize);

    PageInfo<RisCountVO> selectRISCount(Params paramsobject, Integer pageNum, Integer pageSize);

    List<RISOrderInfoVO> getAllRisOrderData(List<RISOrderInfoVO> risOrderInfoVOS);
}

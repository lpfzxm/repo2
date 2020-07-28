package com.dhcens.emviewdoctor.service;

import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.PatientIndexVO;
import com.dhcens.emviewdoctor.entity.VO.PatientInfoVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * TODO
 *
 * @author : zhibao
 */
public interface PatientIndexService {
    /**
     *todo
     *查询患者就诊记录信息
     * @param hosPatientId 患者his登记号
     * @param sexCode   性别代码
     * @param page  页面
     * @param rows  行数
     * @return
     */
    List<PatientIndexVO> selectAll(String hosPatientId,
                                   String sexCode,
                                   String page,
                                   String rows);


    PageInfo<PatientInfoVO> selectPatientInfo(Params paramsobject, Integer pageNum, Integer pageSize);

    String selectPatientId(String tableName, String HosPatientID);
}

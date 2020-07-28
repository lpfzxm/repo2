package com.dhcens.emviewdoctor.service.impl;


import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.PatientIndexVO;
import com.dhcens.emviewdoctor.entity.VO.PatientInfoVO;
import com.dhcens.emviewdoctor.mapper.PatientIndexMapper;
import com.dhcens.emviewdoctor.mapper.PatientInfoMapper;
import com.dhcens.emviewdoctor.service.PatientIndexService;
import com.dhcens.emviewdoctor.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author : zhibao
 */
@Service
public class PatientIndexServiceImpl implements PatientIndexService {

    @Resource
    PatientIndexMapper patientIndexMapper;

    @Resource
    PatientInfoMapper patientInfoMapper;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public List<PatientIndexVO> selectAll(String hosPatientId, String sexCode, String page, String rows) {
        int temprows = Integer.parseInt(rows);
        int temppage = (Integer.parseInt(page) - 1) * temprows;
        return patientIndexMapper.selectAll(hosPatientId, sexCode, temppage, temprows);
    }

    @Override
    public PageInfo<PatientInfoVO> selectPatientInfo(Params paramsobject, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PatientInfoVO> patientInfoVOS = patientInfoMapper.selectPatientInfo( paramsobject);


        PageInfo<PatientInfoVO> pageInfo = new PageInfo<>(patientInfoVOS);
        return pageInfo;
    }

    @Override
    public String selectPatientId(String tableName, String HosPatientID) {
        String PATPatientID=null;
        PATPatientID= (String) redisUtil.hget(tableName,HosPatientID);
        if(StringUtils.isBlank(PATPatientID)){
            PATPatientID=patientIndexMapper.selectPatpatientId(HosPatientID);
            redisUtil.hset(tableName,HosPatientID,PATPatientID);
        }
        return PATPatientID;
    }
}

package com.dhcens.emviewdoctor.service.impl;

import com.dhcens.emviewdoctor.mapper.PatientIndexMapper;
import com.dhcens.emviewdoctor.service.PatPatientIdService;
import com.dhcens.emviewdoctor.util.RedisUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName PatPatientIdServiceImpl
 * @Description: TODO
 * @Author lipengfei
 * @Date 2019/12/24
 * @Version V1.0
 **/
@Service
public class PatPatientIdServiceImpl implements PatPatientIdService {

    @Autowired
    private PatientIndexMapper patientIndexMapper;

    @Autowired
    private RedisUtil redisUtil;

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

package com.dhcens.emviewdoctor.service.impl;

import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.PaallergyVO;
import com.dhcens.emviewdoctor.exception.ExceptionEnum;
import com.dhcens.emviewdoctor.exception.PlException;
import com.dhcens.emviewdoctor.mapper.PaallergyMapper;
import com.dhcens.emviewdoctor.mapper.PatientIndexMapper;
import com.dhcens.emviewdoctor.service.DictToRedisService;
import com.dhcens.emviewdoctor.service.PaallergyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

@Service
public class PaallergyServiceImpl implements PaallergyService {
    @Autowired
    private PaallergyMapper paallergyMapper;
    @Resource
    DictToRedisService dictToRedisService;

    @Autowired
    private PatientIndexMapper patientIndexMapper;
    @Override
    public PageInfo<PaallergyVO> selectPaallergy(Params paramsobject, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        String hosPatientID = paramsobject.getHosPatientID();
        String patPatientID = paramsobject.getPATPatientID();
        if (StringUtils.isBlank(patPatientID)&& StringUtils.isBlank(hosPatientID)){
            throw new PlException(ExceptionEnum.INVALID_PARAM);
        }
        if (StringUtils.isBlank(patPatientID)&& StringUtils.isNotBlank(hosPatientID)){
            paramsobject.setPATPatientID(patientIndexMapper.selectPatpatientId(hosPatientID));
        }
            List<PaallergyVO> paallergyVOS = paallergyMapper.selectPaallergy(paramsobject);
            PageInfo<PaallergyVO> pageInfo=new PageInfo<>(paallergyVOS);
            List<PaallergyVO> PaallergyVOSOut=this.getAllPaallergyData(pageInfo.getList());
            pageInfo.setList(PaallergyVOSOut);
            return pageInfo;

    }

    @Override
    public List<PaallergyVO> getAllPaallergyData(List<PaallergyVO> paallergyVOS) {
        List<PaallergyVO> PaallergyVOSOut=new ArrayList<PaallergyVO>();
        try {
            for(int i=0;i<paallergyVOS.size();i++){
                PaallergyVO paallergyVO=paallergyVOS.get(i);
                String paallupdateusercode=paallergyVO.getPaallupdateusercode();
                if(StringUtils.isNotBlank(paallupdateusercode)){
                    paallergyVO.setPaallupdateuserdesc(dictToRedisService.selectDescValueByTableNameAndCode("ss_user",paallupdateusercode));
                }
                PaallergyVOSOut.add(i,paallergyVO);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return PaallergyVOSOut;
    }


}

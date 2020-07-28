package com.dhcens.emviewdoctor.service.impl;

import com.dhcens.emviewdoctor.entity.RT.ObserInfoRT;
import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.ObservationsVO;
import com.dhcens.emviewdoctor.exception.ExceptionEnum;
import com.dhcens.emviewdoctor.exception.LyException;
import com.dhcens.emviewdoctor.mapper.ObservationsMapper;
import com.dhcens.emviewdoctor.service.DictToRedisService;
import com.dhcens.emviewdoctor.service.ObserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * @author lipengfei <lipengfei_ylrj@dhcc.com><br/>
 * @date 2020/1/9
 */
@Service
public class ObserServiceImpl implements ObserService {
    @Autowired
    private ObservationsMapper observationsMapper;

    @Resource
    DictToRedisService dictToRedisService;

    @Override
    public PageInfo<ObservationsVO> selectObservations(Params paramsobject, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        if((StringUtils.isBlank(paramsobject.getPatVisitNumber()))&& StringUtils.isBlank(paramsobject.getPATPatientID())){
            throw new LyException(ExceptionEnum.INVALID_PARAM);
        }
        System.out.println("-------------------------");
        ObserInfoRT obserInfo=paramsobject.getObserInfo();
        List<ObservationsVO> observationsVOS = observationsMapper.selectObservations(paramsobject,obserInfo);
        PageInfo<ObservationsVO> pageInfo=new PageInfo<ObservationsVO>(observationsVOS);
        List<ObservationsVO> observationsVOSOut=this.getAllObservationsData(pageInfo.getList());
        pageInfo.setList(observationsVOSOut);
        return pageInfo;
    }

    @Override
    public List<ObservationsVO> getAllObservationsData(List<ObservationsVO> observationsVOS) {
        List<ObservationsVO> observationsVOSOut=new ArrayList<ObservationsVO>();
        try{
            for (int i=0;i<observationsVOS.size();i++){
                ObservationsVO observationsVO = observationsVOS.get(i);
                String paoobsercode = observationsVO.getPaoobsercode();
                String paoobserupdateusercode = observationsVO.getPaoobserupdateusercode();
                if(StringUtils.isNotBlank(paoobserupdateusercode)){
                    observationsVO.setPaoobserupdateuserdesc(dictToRedisService.selectDescValueByTableNameAndCode("ss_user",paoobserupdateusercode));

                }
                if(StringUtils.isNotBlank(paoobsercode)){
                    observationsVO.setPaoobserdesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_observationitem",paoobsercode));

                }
                observationsVOSOut.add(i,observationsVO);
            }
            return observationsVOSOut;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

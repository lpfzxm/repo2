package com.dhcens.emviewdoctor.service.impl;


import com.dhcens.emviewdoctor.entity.RT.PADDiagInfoRT;
import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.DiagnoseVO;
import com.dhcens.emviewdoctor.mapper.DiagnoseMapper;
import com.dhcens.emviewdoctor.service.DiagnoseService;
import com.dhcens.emviewdoctor.service.DictToRedisService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * @ClassName DiagnoseServiceImpl
 * @Description: TODO
 * @Author lipengfei
 * @Date 2019/12/27
 * @Version V1.0
 **/
@Service
public class DiagnoseServiceImpl implements DiagnoseService {
    @Autowired
    private DiagnoseMapper diagnoseMapper;
    @Resource
    DictToRedisService dictToRedisService;
    @Override
    public PageInfo<DiagnoseVO> selectDiagnose(Params paramsobject, Integer pageNum, Integer pageSize) {
        if ((pageNum!=null)&&(pageSize!=null))
        {
            PageHelper.startPage(pageNum, pageSize);
        }else {
            PageHelper.startPage(1, 0, true, true, true);
        }
        PADDiagInfoRT padDiagInfo=paramsobject.getPadDiagInfo();
        if(StringUtils.isBlank(paramsobject.getPATPatientID())||StringUtils.isBlank(paramsobject.getPatVisitNumber())||StringUtils.isBlank(paramsobject.getHosPatientID())|| CollectionUtils.isEmpty(paramsobject.getPatVisitNumberIndex())){
            System.out.println("---");

            //PageInfo<T> pageInfo=new PageInfo<T>(ExceptionEnum.INVALID_PARAM);
            //throw new PlException(ExceptionEnum.INVALID_PARAM);
            System.out.println("异常");
        }
        List<DiagnoseVO> diagnoseVOS=diagnoseMapper.selectDiagnoseByCondition(paramsobject,padDiagInfo);
        PageInfo<DiagnoseVO> pageInfo = new PageInfo<>(diagnoseVOS);
        List<DiagnoseVO> diagnoseVOSOut=this.getAllDiagnoseData(pageInfo.getList());
        pageInfo.setList(diagnoseVOSOut);
        return pageInfo;
    }

    @Override
    public List<DiagnoseVO> getAllDiagnoseData(List<DiagnoseVO> diagnoseVOS) {
        List<DiagnoseVO> diagnoseVOSOut=new ArrayList<DiagnoseVO>();
        try {
            for (int i=0;i<diagnoseVOS.size();i++){
                DiagnoseVO tmpdiagnoseVO=diagnoseVOS.get(i);


                String diagCode = tmpdiagnoseVO.getPADDiagCode();
                String diagDesc = tmpdiagnoseVO.getPADDiagDesc();
                if ((StringUtils.isNotBlank(diagCode))&&(!StringUtils.isNotBlank(diagDesc)))
                {
                    tmpdiagnoseVO.setPADDiagDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_diagnose",diagCode));
                }
                String padssUserCode = tmpdiagnoseVO.getPADSSUserCode();
                if(StringUtils.isNotBlank(padssUserCode)){
                    tmpdiagnoseVO.setPADDiagDocCode(padssUserCode);
                    String padssUserDesc = dictToRedisService.selectDescValueByTableNameAndCode("ss_user", padssUserCode);
                    tmpdiagnoseVO.setPADSSUserDesc(padssUserDesc);
                    tmpdiagnoseVO.setPADDiagDocDesc(padssUserDesc);
                }
                String padDiagTypeCode = tmpdiagnoseVO.getPADDiagTypeCode();
                if(StringUtils.isNotBlank(padDiagTypeCode)){
                    tmpdiagnoseVO.setPADDiagTypeDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_diagnosetype",padDiagTypeCode));
                }
                diagnoseVOSOut.add(i,tmpdiagnoseVO);
            }

            return diagnoseVOSOut;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

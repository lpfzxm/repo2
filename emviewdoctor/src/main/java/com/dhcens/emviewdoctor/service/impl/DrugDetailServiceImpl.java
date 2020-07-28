package com.dhcens.emviewdoctor.service.impl;

import com.dhcens.emviewdoctor.entity.RT.OEORIItemInfoRT;
import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.DrugDetailVO;
import com.dhcens.emviewdoctor.mapper.DrugDetailMapper;
import com.dhcens.emviewdoctor.service.DictToRedisService;
import com.dhcens.emviewdoctor.service.DrugDetailService;
import com.dhcens.emviewdoctor.service.PatPatientIdService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * @ClassName DrugDetailServiceImpl
 * @Description: TODO
 * @Author 18444
 * @Date 2019/12/17
 * @Version V1.0
 **/

@Service
public class DrugDetailServiceImpl implements DrugDetailService {

    @Autowired
    private DrugDetailMapper drugDetailMapper;
    @Resource
    DictToRedisService dictToRedisService;
    @Autowired
    private PatPatientIdService patPatientIdService;

    @Override
    public PageInfo<DrugDetailVO> selectDrugList(Params paramsobject, Integer pageNum, Integer pageSize) {
        if (StringUtils.isNotBlank(paramsobject.getPatVisitNumber())){
            paramsobject.setPATPatientID("");
            paramsobject.setHosPatientID("");
        }

        if (StringUtils.isBlank(paramsobject.getPATPatientID())&&StringUtils.isNotBlank(paramsobject.getHosPatientID())){
            System.out.println("----------");
            String PATPatientID = patPatientIdService.selectPatientId("rel_patient",paramsobject.getHosPatientID());
            paramsobject.setPATPatientID(PATPatientID);
        }

        if ((pageNum!=null)&&(pageSize!=null))
        {
            PageHelper.startPage(pageNum, pageSize);
        }else {
            PageHelper.startPage(1, 0, true, true, true);
        }
        OEORIItemInfoRT oeoriItemInfo = paramsobject.getOEORIItemInfo();
        List<DrugDetailVO> drugDetailVOS = drugDetailMapper.selectDrugDetailByCondition(paramsobject,oeoriItemInfo);
        PageInfo<DrugDetailVO> pageInfo = new PageInfo<DrugDetailVO>(drugDetailVOS);

        List<DrugDetailVO> drugDetailVOSOut = this.getAllDrugDateilData(pageInfo.getList());

        pageInfo.setList(drugDetailVOSOut);
        return pageInfo;
    }

    @Override
    public List<DrugDetailVO> getAllDrugDateilData(List<DrugDetailVO> drugDetailVOS) {
        List<DrugDetailVO> drugDetailVOSOut = new ArrayList<DrugDetailVO>();


        try {
            for (int i = 0; i < drugDetailVOS.size(); i++) {
                DrugDetailVO tmpdrugdetail = drugDetailVOS.get(i);
                String doseUnitCode = tmpdrugdetail.getOEORIDoseUnitCode();
                if (StringUtils.isNotBlank(doseUnitCode)) {
                    tmpdrugdetail.setOEORIDoseUnitDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_phunit", doseUnitCode));
                }
                String freqCode = tmpdrugdetail.getOEORIFreqCode();
                if (StringUtils.isNotBlank(freqCode)) {
                    tmpdrugdetail.setOEORIFreqDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_freq", freqCode));

                }

                String instrCode = tmpdrugdetail.getOEORIInstrCode();
                if (StringUtils.isNotBlank(instrCode)) {
                    tmpdrugdetail.setOEORIInstrDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_instr", instrCode));
                }

                String priorityCode = tmpdrugdetail.getOEORIPriorityCode();
                if (StringUtils.isNotBlank(priorityCode)) {
                    tmpdrugdetail.setOEORIPriorityDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_priority", priorityCode));
                }
                String stopDocCode = tmpdrugdetail.getOEORIStopDocCode();
                if (StringUtils.isNotBlank(stopDocCode)) {
                    tmpdrugdetail.setOEORIStopDocDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_careprov", stopDocCode));
                }
                String enterDocCode = tmpdrugdetail.getOEORIEnterDocCode();
                if (StringUtils.isNotBlank(enterDocCode)) {
                    tmpdrugdetail.setOEORIEnterDocDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_careprov", enterDocCode));
                }

                String enterDeptCode = tmpdrugdetail.getOEORIEnterDeptCode();
                if (StringUtils.isNotBlank(enterDeptCode)) {

                    String tmpstr = dictToRedisService.selectDescValueByTableNameAndCode("ct_dept", enterDeptCode);
                    if (!tmpstr.contains("-"))
                    {
                        tmpdrugdetail.setOEORIEnterDeptDesc(tmpstr);
                    }else{
                        tmpdrugdetail.setOEORIEnterDeptDesc(tmpstr.substring(tmpstr.indexOf("-")+1));
                    }

                }
                String durationcode = tmpdrugdetail.getOEORIDurationCode();
                if (StringUtils.isNotBlank(durationcode)) {
                    tmpdrugdetail.setOEORIDurationDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_duration", durationcode));
                }
                String execDeptCode = tmpdrugdetail.getOEORIExecDeptCode();
                if (StringUtils.isNotBlank(execDeptCode)) {

                    String tmpstr = dictToRedisService.selectDescValueByTableNameAndCode("ct_dept", execDeptCode);
                    if (!tmpstr.contains("-"))
                    {
                        tmpdrugdetail.setOEORIExecDeptDesc(tmpstr);
                    }else{
                        tmpdrugdetail.setOEORIExecDeptDesc(tmpstr.substring(tmpstr.indexOf("-")+1));
                    }
                }
                String statusCode = tmpdrugdetail.getOEORIStatusCode();
                if (StringUtils.isNotBlank(statusCode)) {
                    tmpdrugdetail.setOEORIStatusDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_orderstatus",statusCode));
                }

                String stopDate = tmpdrugdetail.getOEORIStopDate();
                if (StringUtils.isNotBlank(stopDate)) {

                    if (!stopDate.contains(" "))
                    {
                        tmpdrugdetail.setOEORIStopDate(stopDate);
                    }else{
                        tmpdrugdetail.setOEORIStopDate(stopDate.substring(0,stopDate.indexOf(" ")));
                    }
                }


                String enterDate = tmpdrugdetail.getOEORIEnterDate();
                if (StringUtils.isNotBlank(enterDate)) {

                    if (!enterDate.contains(" "))
                    {
                        tmpdrugdetail.setOEORIEnterDate(enterDate);
                    }else{
                        tmpdrugdetail.setOEORIEnterDate(enterDate.substring(0,enterDate.indexOf(" ")));
                    }

                }
                drugDetailVOSOut.add(i, tmpdrugdetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return drugDetailVOSOut;
    }
}

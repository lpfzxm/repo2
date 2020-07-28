package com.dhcens.emviewdoctor.service.impl;


import com.dhcens.emviewdoctor.entity.RT.OEORIItemInfoRT;
import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.OeordItemVO;
import com.dhcens.emviewdoctor.mapper.OeordItemlMapper;
import com.dhcens.emviewdoctor.service.DictToRedisService;
import com.dhcens.emviewdoctor.service.OeordItemService;
import com.dhcens.emviewdoctor.service.PatPatientIdService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

@Service
public class OeordItemServiceImpl implements OeordItemService {

    @Resource
    OeordItemlMapper oeordItemlMapper;
    @Resource
    DictToRedisService dictToRedisService;

    @Resource
    PatPatientIdService patPatientIdService;

    @Override
    public PageInfo<OeordItemVO> selectOeordItemByCondiction(Params paramsobject, Integer pageNum, Integer pageSize) {

        if (StringUtils.isNotBlank(paramsobject.getPatVisitNumber())) {
            paramsobject.setPATPatientID("");
            paramsobject.setHosPatientID("");
        }
        if (StringUtils.isBlank(paramsobject.getPATPatientID()) && StringUtils.isNotBlank(paramsobject.getHosPatientID())) {

                String PATPatientID = patPatientIdService.selectPatientId("rel_patient", paramsobject.getHosPatientID());
                paramsobject.setPATPatientID(PATPatientID);
        }
        if ((pageNum!=null)&&(pageSize!=null))
        {
            PageHelper.startPage(pageNum, pageSize);
        }else {
            PageHelper.startPage(1, 0, true, true, true);
        }
        OEORIItemInfoRT oeoriItemInfo = paramsobject.getOEORIItemInfo();
        List<OeordItemVO> drugDetailVOS = oeordItemlMapper.selectOeordItemByCondition(paramsobject, oeoriItemInfo);
        PageInfo<OeordItemVO> pageInfo = new PageInfo<OeordItemVO>(drugDetailVOS);
        List<OeordItemVO> oeordItemVoOut = this.getAllOeordItem(pageInfo.getList());
        pageInfo.setList(oeordItemVoOut);
        return pageInfo;
    }

    @Override
    public List<OeordItemVO> getAllOeordItem(List<OeordItemVO> oeordItemVoList) {
        List<OeordItemVO> oeordItemVoOut = new ArrayList<OeordItemVO>();
        try {
            for (int i = 0; i < oeordItemVoList.size(); i++) {
                OeordItemVO tempoeordItem = oeordItemVoList.get(i);
                String doseUnitCode = tempoeordItem.getOeoriDoseUnitCode();
                if (StringUtils.isNotBlank(doseUnitCode)) {
                    tempoeordItem.setOeoriDoseUnitDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_phunit", doseUnitCode));
                }
                String freqCode = tempoeordItem.getOeoriFreqCode();
                if (StringUtils.isNotBlank(freqCode)) {
                    tempoeordItem.setOeoriFreqDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_freq", freqCode));

                }
                String instrCode = tempoeordItem.getOeoriInstrCode();
                if (StringUtils.isNotBlank(instrCode)) {
                    tempoeordItem.setOeoriInstrDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_instr", instrCode));
                }

                String priorityCode = tempoeordItem.getOeoriPriorityCode();
                if (StringUtils.isNotBlank(priorityCode)) {
                    tempoeordItem.setOeoriPriorityDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_priority", priorityCode));
                }
                String stopDocCode = tempoeordItem.getOeoriStopDocCode();
                if (StringUtils.isNotBlank(stopDocCode)) {
                    tempoeordItem.setOeoriStopDocDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_careprov", stopDocCode));
                }
                String enterDocCode = tempoeordItem.getOeoriEnterDocCode();
                if (StringUtils.isNotBlank(enterDocCode)) {
                    tempoeordItem.setOeoriEnterDocDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_careprov", enterDocCode));
                }

                String enterDeptCode = tempoeordItem.getOeoriEnterDeptCode();
                if (StringUtils.isNotBlank(enterDeptCode)) {

                    String tmpstr = dictToRedisService.selectDescValueByTableNameAndCode("ct_dept", enterDeptCode);
                    if (!tmpstr.contains("-")) {
                        tempoeordItem.setOeoriEnterDeptDesc(tmpstr);
                    } else {
                        tempoeordItem.setOeoriEnterDeptDesc(tmpstr.substring(tmpstr.indexOf("-")+1));
                    }
                }
                String durationcode = tempoeordItem.getOeoriDurationCode();
                if (StringUtils.isNotBlank(durationcode)) {
                    tempoeordItem.setOeoriDurationDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_duration", durationcode));
                }
                String execDeptCode = tempoeordItem.getOeoriExecDeptCode();
                if (StringUtils.isNotBlank(execDeptCode)) {
                    String tmpstr = dictToRedisService.selectDescValueByTableNameAndCode("ct_dept", execDeptCode);
                    if (!tmpstr.contains("-")) {
                        tempoeordItem.setOeoriExecDeptDesc(tmpstr);
                    } else {
                        tempoeordItem.setOeoriExecDeptDesc(tmpstr.substring(tmpstr.indexOf("-")+1));
                    }
                }
                String statusCode = tempoeordItem.getOeoriStatusCode();
                if (StringUtils.isNotBlank(statusCode)) {
                    tempoeordItem.setOeoriStatusDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_orderstatus", statusCode));
                }
                String stopDate = tempoeordItem.getOeoriStopDate();
                if (StringUtils.isNotBlank(stopDate)) {
                    if (!stopDate.contains(" ")) {
                        tempoeordItem.setOeoriStopDate(stopDate);
                    } else {
                        tempoeordItem.setOeoriStopDate(stopDate.substring(0, stopDate.indexOf(" ")));
                    }
                }
                String enterDate = tempoeordItem.getOeoriEnterDate();
                if (StringUtils.isNotBlank(enterDate)) {
                    if (!enterDate.contains(" ")) {
                        tempoeordItem.setOeoriEnterDate(enterDate);
                    } else {
                        tempoeordItem.setOeoriEnterDate(enterDate.substring(0, enterDate.indexOf(" ")));
                    }
                }
                oeordItemVoOut.add(i, tempoeordItem);
            }
            return oeordItemVoOut;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}

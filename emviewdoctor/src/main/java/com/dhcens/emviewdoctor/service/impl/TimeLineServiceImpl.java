package com.dhcens.emviewdoctor.service.impl;


import com.dhcens.emviewdoctor.entity.RT.PAAdmInfoRT;
import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.TimeLineAdmVO;
import com.dhcens.emviewdoctor.entity.VO.TimeLineDiagnoseVO;
import com.dhcens.emviewdoctor.mapper.TimeLineAdmMapper;
import com.dhcens.emviewdoctor.mapper.TimeLineDiagnoseMapper;
import com.dhcens.emviewdoctor.service.DictToRedisService;
import com.dhcens.emviewdoctor.service.TimeLineService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

@Service
public class TimeLineServiceImpl implements TimeLineService {

    @Resource
    TimeLineAdmMapper timeLineAdmMapper;
    @Resource
    TimeLineDiagnoseMapper timeLineDiagnoseMapper;

    @Resource
    DictToRedisService dictToRedisService;

    @Override
    public PageInfo<TimeLineAdmVO> selectTimeLineByCondiction(Params paramsobject, Integer pageNum, Integer pageSize) {
        if ((pageNum!=null)&&(pageSize!=null))
        {
            PageHelper.startPage(pageNum, pageSize);
        }else {
            PageHelper.startPage(1, 0, true, true, true);
        }
        PAAdmInfoRT paAdmInfo=paramsobject.getPAAdmInfo();
        List<TimeLineAdmVO> timeLineAdmVos = timeLineAdmMapper.selectTimeLineAdm(paramsobject,paAdmInfo);
        PageInfo<TimeLineAdmVO> pageInfo=new PageInfo<TimeLineAdmVO>(timeLineAdmVos);
        if(pageInfo.getList().size()>0){
            List<TimeLineAdmVO> timeLineAdmVOS = this.getAllTimeLineAdmInfo(pageInfo.getList());
            pageInfo.setList(timeLineAdmVOS);
        }

        return pageInfo;
    }

    List<TimeLineAdmVO> getAllTimeLineAdmInfo(List<TimeLineAdmVO> timeLinePaadmVoS)
    {
        List<TimeLineAdmVO> timeLinePaadmVoOut = new ArrayList<TimeLineAdmVO>();
        List patVisitNumberList = new ArrayList();
        for (int i = 0; i <timeLinePaadmVoS.size() ; i++) {
            TimeLineAdmVO timeLineadmVo = timeLinePaadmVoS.get(i);
            //从redis缓存中获取字典信息
            String patVisitNumber = timeLineadmVo.getPaadmVisitNumber();
            if (StringUtils.isNotBlank(patVisitNumber)) {
                patVisitNumberList.add(patVisitNumber);

            }
        }
        List<TimeLineDiagnoseVO> timeLineDiagnoseVoList = new ArrayList<>();
        if (patVisitNumberList.size()>0) {
             timeLineDiagnoseVoList = selectPadiagnoseInfo(patVisitNumberList);
        }



        for (int i = 0; i <timeLinePaadmVoS.size() ; i++) {
            TimeLineAdmVO timeLineadmVo = timeLinePaadmVoS.get(i);
            String patVisitNumber = timeLineadmVo.getPaadmVisitNumber();
            if(StringUtils.isNotBlank(patVisitNumber))
            {
                timeLineadmVo.setDiaginfo(getPadiagnoseInfoByVisitnumber(patVisitNumber,timeLineDiagnoseVoList));
            }
            String admitDocCode = timeLineadmVo.getPaadmAdmitDocCode();
            if(StringUtils.isNotBlank(admitDocCode))
            {
                String tempstr = dictToRedisService.selectDescValueByTableNameAndCode("ct_careprov", admitDocCode);
                timeLineadmVo.setPaadmAdmitDocDesc(tempstr);
            }

            String encounterTypeCode = timeLineadmVo.getPaadmEncounterTypeCode();
            if(StringUtils.isNotBlank(encounterTypeCode))
            {
                String tempstr = dictToRedisService.selectDescValueByTableNameAndCode("ct_encountertype", encounterTypeCode);
                timeLineadmVo.setPaadmEncounterTypeDesc(tempstr);
            }

            String attendDocCode = timeLineadmVo.getPaadmAttendDocCode();
            if(StringUtils.isNotBlank(attendDocCode))
            {
                String tempstr = dictToRedisService.selectDescValueByTableNameAndCode("ct_careprov", attendDocCode);
                timeLineadmVo.setPaadmAttendDocDesc(tempstr);
            }
            String admDeptCode = timeLineadmVo.getPaadmAdmDeptCode();
            if(StringUtils.isNotBlank(admDeptCode))
            {
                String tempstr = dictToRedisService.selectDescValueByTableNameAndCode("ct_dept", admDeptCode);
                if (!tempstr.contains("-")){
                    timeLineadmVo.setPaadmAdmDeptDesc(tempstr);
                }
                else{
                    timeLineadmVo.setPaadmAdmDeptDesc(tempstr.substring(tempstr.indexOf("-")+1));
                }
            }
            String disDeptCode = timeLineadmVo.getPaadmDisDeptCode();
            if(StringUtils.isNotBlank(disDeptCode))
            {
                String tempstr = dictToRedisService.selectDescValueByTableNameAndCode("ct_dept", disDeptCode);
                if (!tempstr.contains("-")){
                    timeLineadmVo.setPaadmAdmDeptDesc(tempstr);
                }
                else{
                    timeLineadmVo.setPaadmAdmDeptDesc(tempstr.substring(tempstr.indexOf("-")+1));
                }
            }
            String paadmStartDate = timeLineadmVo.getPaadmStartDate();
            if(StringUtils.isNotBlank(paadmStartDate)){
                if(!paadmStartDate.contains(" "))
                {
                    timeLineadmVo.setPaadmStartDate(paadmStartDate);
                }else {
                    timeLineadmVo.setPaadmStartDate(paadmStartDate.substring(0,paadmStartDate.indexOf(" ")));
                }

            }

            String paadmEndDate = timeLineadmVo.getPaadmEndDate();
            if(StringUtils.isNotBlank(paadmEndDate)){
                if (!paadmEndDate.contains(" "))
                {
                    timeLineadmVo.setPaadmEndDate(paadmEndDate);
                }else {
                    timeLineadmVo.setPaadmEndDate(paadmEndDate.substring(0,paadmEndDate.indexOf(" ")));
                }

            }

            timeLinePaadmVoOut.add(i, timeLineadmVo);
        }
        return timeLinePaadmVoOut;
    }


    List<TimeLineDiagnoseVO> selectPadiagnoseInfo(List<String> PatVisitNumberList) {
        return timeLineDiagnoseMapper.selectTimeLineDiagnose(PatVisitNumberList);
    }

    List<TimeLineDiagnoseVO> getPadiagnoseInfoByVisitnumber(String visitnumber,List<TimeLineDiagnoseVO> tempDiagnoseList)
    {
        List<TimeLineDiagnoseVO> diagnoseListOut = new ArrayList<TimeLineDiagnoseVO>();
        try {

            for (int i = 0; i < tempDiagnoseList.size(); i++) {
                TimeLineDiagnoseVO timeLineDiagnose = tempDiagnoseList.get(i);
                String tmpVisitnumber = timeLineDiagnose.getPadVisitNumber();
                if (visitnumber.equals(tmpVisitnumber) )
                {
                    ///处理一下诊断代码，如果存在诊断代码，则从redis中获取数据
                    String diagCode = timeLineDiagnose.getPadDiagCode();
                    String diagDesc = timeLineDiagnose.getPadDiagDesc();
                    if ((StringUtils.isNotBlank(diagCode))&&(!StringUtils.isNotBlank(diagDesc)))
                    {
                        timeLineDiagnose.setPadDiagDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_diagnose",diagCode));
                    }
                    diagnoseListOut.add(timeLineDiagnose);

                }
            }
            return diagnoseListOut;
        }catch (Exception e){
            e.printStackTrace();
        }
        return diagnoseListOut;

    }


}

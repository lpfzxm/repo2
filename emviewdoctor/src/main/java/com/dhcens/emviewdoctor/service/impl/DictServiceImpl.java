package com.dhcens.emviewdoctor.service.impl;

import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.AdmDictVO;
import com.dhcens.emviewdoctor.entity.VO.DrugDictVO;
import com.dhcens.emviewdoctor.mapper.DictMapper;
import com.dhcens.emviewdoctor.service.DictService;
import com.dhcens.emviewdoctor.service.DictToRedisService;
import com.dhcens.emviewdoctor.service.PatientIndexService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.annotation.Resource;

/**
 * @ClassName DictServiceImpl
 * @Description: TODO
 * @Author lipengfei
 * @Date 2020/1/2
 * @Version V1.0
 **/
@Service
public class DictServiceImpl implements DictService {
    @Autowired
    private PatientIndexService patientIndexService;
    @Autowired
    private DictMapper dictMapper;
    @Resource
    DictToRedisService dictToRedisService;

    @Override
    public PageInfo<DrugDictVO> selectDrugDict(Params paramsobject, Integer pageNum, Integer pageSize) {
        if (StringUtils.isNotBlank(paramsobject.getPatVisitNumber())) {
             paramsobject.setPATPatientID("");
            paramsobject.setHosPatientID("");
        }
        if (StringUtils.isBlank(paramsobject.getPATPatientID()) && StringUtils.isNotBlank(paramsobject.getHosPatientID())) {
            System.out.println("----------");
            String PATPatientID = patientIndexService.selectPatientId("rel_patient", paramsobject.getHosPatientID());
            paramsobject.setPATPatientID(PATPatientID);
        }
        PageHelper.startPage(1, 0, true, true, true);
        List<DrugDictVO> drugDictVOS = dictMapper.selectDrugDictByCondition(paramsobject);
        //处理数据
        List<DrugDictVO.CategoryDataBean> categoryDataBeanList = new ArrayList<>();
        List<DrugDictVO.ChildCategoryDataBean> childCategoryDataBeanList=new ArrayList<>();
        List<DrugDictVO.EnterDeptDataBean> enterDeptDataBeanList=new ArrayList<>();
        List<DrugDictVO.PriorityDataBean> priorityDataBeanList=new ArrayList<>();
        for (DrugDictVO drugDictVO : drugDictVOS) {
            if(drugDictVO.getCategoryData()!=null && drugDictVO.getCategoryData().size()!=0) {
                categoryDataBeanList.add(drugDictVO.getCategoryData().get(0));
            }
            if(drugDictVO.getChildCategoryData()!=null && drugDictVO.getChildCategoryData().size()!=0) {
                childCategoryDataBeanList.add(drugDictVO.getChildCategoryData().get(0));
            }
            if(drugDictVO.getEnterDeptData()!=null && drugDictVO.getEnterDeptData().size()!=0){
                drugDictVO.getEnterDeptData().get(0).setEnterDeptDesc( dictToRedisService.selectDescValueByTableNameAndCode("ct_dept",drugDictVO.getEnterDeptData().get(0).getEnterDeptCode()));
                enterDeptDataBeanList.add(drugDictVO.getEnterDeptData().get(0));
            }
            if(drugDictVO.getPriorityData()!=null && drugDictVO.getPriorityData().size()!=0){
                drugDictVO.getPriorityData().get(0).setPriorityDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_priority",drugDictVO.getPriorityData().get(0).getPriorityCode()));
                priorityDataBeanList.add(drugDictVO.getPriorityData().get(0));
            }
        }
        List<DrugDictVO.CategoryDataBean> collectCategoryDataBean = categoryDataBeanList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()
                -> new TreeSet<>(Comparator.comparing(DrugDictVO.CategoryDataBean::getCategoryCode))), ArrayList::new));

        List<DrugDictVO.ChildCategoryDataBean> collectChildCategoryDataBean = childCategoryDataBeanList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()
                -> new TreeSet<>(Comparator.comparing(DrugDictVO.ChildCategoryDataBean::getChildCategoryCode))), ArrayList::new));

        List<DrugDictVO.EnterDeptDataBean> collectEnterDeptDataBean = enterDeptDataBeanList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()
                -> new TreeSet<>(Comparator.comparing(DrugDictVO.EnterDeptDataBean::getEnterDeptCode))), ArrayList::new));

        List<DrugDictVO.PriorityDataBean> collectPriorityDataBean = priorityDataBeanList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()
                -> new TreeSet<>(Comparator.comparing(DrugDictVO.PriorityDataBean::getPriorityCode))), ArrayList::new));
        DrugDictVO drugDictVO = new DrugDictVO();
        drugDictVO.setCategoryData(collectCategoryDataBean);
        drugDictVO.setChildCategoryData(collectChildCategoryDataBean);
        drugDictVO.setEnterDeptData(collectEnterDeptDataBean);
        drugDictVO.setPriorityData(collectPriorityDataBean);
        List<DrugDictVO> drugDictVOList = new ArrayList<>();
        drugDictVOList.add(drugDictVO);
        return new PageInfo<>(drugDictVOList);
    }


    @Override
    public PageInfo<DrugDictVO> selectOEordDict(Params paramsobject, Integer pageNum, Integer pageSize) {
        if (StringUtils.isNotBlank(paramsobject.getPatVisitNumber())) {
            paramsobject.setPATPatientID("");
            paramsobject.setHosPatientID("");
        }
        if (StringUtils.isBlank(paramsobject.getPATPatientID()) && StringUtils.isNotBlank(paramsobject.getHosPatientID())) {
            System.out.println("----------");
            String PATPatientID = patientIndexService.selectPatientId("rel_patient", paramsobject.getHosPatientID());
            paramsobject.setPATPatientID(PATPatientID);
        }
        PageHelper.startPage(1, 0, true, true, true);
        List<DrugDictVO> drugDictVOS = dictMapper.selectOEordDictByCondition(paramsobject);
        //处理数据
        List<DrugDictVO.CategoryDataBean> categoryDataBeanList = new ArrayList<>();
        List<DrugDictVO.ChildCategoryDataBean> childCategoryDataBeanList=new ArrayList<>();
        List<DrugDictVO.EnterDeptDataBean> enterDeptDataBeanList=new ArrayList<>();
        List<DrugDictVO.PriorityDataBean> priorityDataBeanList=new ArrayList<>();
        for (DrugDictVO drugDictVO : drugDictVOS) {
            if(drugDictVO.getCategoryData()!=null && drugDictVO.getCategoryData().size()!=0) {
                categoryDataBeanList.add(drugDictVO.getCategoryData().get(0));
            }
            if(drugDictVO.getChildCategoryData()!=null && drugDictVO.getChildCategoryData().size()!=0) {
                childCategoryDataBeanList.add(drugDictVO.getChildCategoryData().get(0));
            }
            if(drugDictVO.getEnterDeptData()!=null && drugDictVO.getEnterDeptData().size()!=0){
                drugDictVO.getEnterDeptData().get(0).setEnterDeptDesc( dictToRedisService.selectDescValueByTableNameAndCode("ct_dept",drugDictVO.getEnterDeptData().get(0).getEnterDeptCode()));
                enterDeptDataBeanList.add(drugDictVO.getEnterDeptData().get(0));
            }
            if(drugDictVO.getPriorityData()!=null && drugDictVO.getPriorityData().size()!=0){
                drugDictVO.getPriorityData().get(0).setPriorityDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_priority",drugDictVO.getPriorityData().get(0).getPriorityCode()));
                priorityDataBeanList.add(drugDictVO.getPriorityData().get(0));
            }

        }
        List<DrugDictVO.CategoryDataBean> collectCategoryDataBean = categoryDataBeanList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()
                -> new TreeSet<>(Comparator.comparing(DrugDictVO.CategoryDataBean::getCategoryCode))), ArrayList::new));

        List<DrugDictVO.ChildCategoryDataBean> collectChildCategoryDataBean = childCategoryDataBeanList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()
                -> new TreeSet<>(Comparator.comparing(DrugDictVO.ChildCategoryDataBean::getChildCategoryCode))), ArrayList::new));

        List<DrugDictVO.EnterDeptDataBean> collectEnterDeptDataBean = enterDeptDataBeanList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()
                -> new TreeSet<>(Comparator.comparing(DrugDictVO.EnterDeptDataBean::getEnterDeptCode))), ArrayList::new));

        List<DrugDictVO.PriorityDataBean> collectPriorityDataBean = priorityDataBeanList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()
                -> new TreeSet<>(Comparator.comparing(DrugDictVO.PriorityDataBean::getPriorityCode))), ArrayList::new));
        DrugDictVO drugDictVO = new DrugDictVO();
        drugDictVO.setCategoryData(collectCategoryDataBean);
        drugDictVO.setChildCategoryData(collectChildCategoryDataBean);
        drugDictVO.setEnterDeptData(collectEnterDeptDataBean);
        drugDictVO.setPriorityData(collectPriorityDataBean);
        List<DrugDictVO> drugDictVOList = new ArrayList<>();
        drugDictVOList.add(drugDictVO);
        return new PageInfo<>(drugDictVOList);
    }

    @Override
    public PageInfo<AdmDictVO> selectAdmDict(Params paramsobject, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(1, 0, true, true, true);
        List<AdmDictVO> admDictVOS = dictMapper.selectAdmDictByCondition(paramsobject);

        //处理数据
        List<AdmDictVO.PAADMAdmDeptListBean> PAADMAdmDeptBeanList=new ArrayList<>();
        List<AdmDictVO.PAADMAttendDocListBean> PAADMAttendDocBeanList=new ArrayList<>();
        List<AdmDictVO.PAADMEncounterTypeListBean> PAADMEncounterTypeBeanList=new ArrayList<>();
        for (AdmDictVO admDictVO : admDictVOS) {
            if(admDictVO.getPAADMAdmDeptList()!=null && admDictVO.getPAADMAdmDeptList().size()!=0) {
                admDictVO.getPAADMAdmDeptList().get(0).setPAADMAdmDeptDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_dept",admDictVO.getPAADMAdmDeptList().get(0).getPAADMAdmDeptCode()));
                PAADMAdmDeptBeanList.add(admDictVO.getPAADMAdmDeptList().get(0));
            }
            if(admDictVO.getPAADMEncounterTypeList()!=null && admDictVO.getPAADMEncounterTypeList().size()!=0) {
                admDictVO.getPAADMEncounterTypeList().get(0).setPAADMEncounterTypeDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_encountertype",admDictVO.getPAADMEncounterTypeList().get(0).getPAADMEncounterTypeCode()));
                PAADMEncounterTypeBeanList.add(admDictVO.getPAADMEncounterTypeList().get(0));
            }
            if(admDictVO.getPAADMAttendDocList()!=null && admDictVO.getPAADMAttendDocList().size()!=0) {
                admDictVO.getPAADMAttendDocList().get(0).setPAADMAttendDocDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_careprov",admDictVO.getPAADMAttendDocList().get(0).getPAADMAttendDocCode()));
                PAADMAttendDocBeanList.add(admDictVO.getPAADMAttendDocList().get(0));
            }

        }
        List<AdmDictVO.PAADMAdmDeptListBean> collectAdmDept = PAADMAdmDeptBeanList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()
                -> new TreeSet<>(Comparator.comparing(AdmDictVO.PAADMAdmDeptListBean::getPAADMAdmDeptCode))), ArrayList::new));
        List<AdmDictVO.PAADMAttendDocListBean> collectADMAttendDoc = PAADMAttendDocBeanList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()
                -> new TreeSet<>(Comparator.comparing(AdmDictVO.PAADMAttendDocListBean::getPAADMAttendDocCode))), ArrayList::new));
        List<AdmDictVO.PAADMEncounterTypeListBean> collectADMEncounterType = PAADMEncounterTypeBeanList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()
                -> new TreeSet<>(Comparator.comparing(AdmDictVO.PAADMEncounterTypeListBean::getPAADMEncounterTypeCode))), ArrayList::new));

        AdmDictVO admDictVO = new AdmDictVO();
        admDictVO.setPAADMAdmDeptList(collectAdmDept);
        admDictVO.setPAADMAttendDocList(collectADMAttendDoc);
        admDictVO.setPAADMEncounterTypeList(collectADMEncounterType);
        List<AdmDictVO> admDictVOList=new ArrayList<>();
        admDictVOList.add(admDictVO);


        return new PageInfo<>(admDictVOList);
    }
}

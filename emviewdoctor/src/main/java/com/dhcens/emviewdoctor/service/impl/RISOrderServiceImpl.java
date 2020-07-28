package com.dhcens.emviewdoctor.service.impl;


import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.RISOrderInfoVO;
import com.dhcens.emviewdoctor.entity.VO.RisCountVO;
import com.dhcens.emviewdoctor.mapper.RISOrderMapper;
import com.dhcens.emviewdoctor.service.DictToRedisService;
import com.dhcens.emviewdoctor.service.RISOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

/**
 * @ClassName RISOrderServiceImpl
 * @Description: TODO
 * @Author lipengfei
 * @Date 2019/12/27
 * @Version V1.0
 **/
@Service
public class RISOrderServiceImpl implements RISOrderService {

    @Autowired
    private RISOrderMapper risOrderMapper;
    @Resource
    DictToRedisService dictToRedisService;

    @Override
    public PageInfo<RISOrderInfoVO> selectRISOrder(Params paramsobject, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(1,0,true,true,true);
        //PageHelper.startPage(pageNum, pageSize);
        List<RISOrderInfoVO> risOrderInfoVOS = risOrderMapper.selectRISOrderByCondition(paramsobject);
        PageInfo<RISOrderInfoVO> pageInfo = new PageInfo<>(risOrderInfoVOS);
        // 查询数据
        List<RISOrderInfoVO> orderInfoVOSOut = this.getAllRisOrderData(pageInfo.getList());
        // 处理数据
        String risrReportDate = paramsobject.getRISRReportDate();
        String risrReportDocDesc = paramsobject.getRISRReportDocDesc();
        if (StringUtils.isNotBlank(risrReportDate) && StringUtils.isBlank(risrReportDocDesc)) {
            List<RISOrderInfoVO> collectByDate = orderInfoVOSOut.stream()
                    .filter(ris -> StringUtils.isNotBlank(ris.getRISRReportDate()))
                    .filter(or -> or.getRISRReportDate().contains(risrReportDate))
                    .sorted(Comparator.comparing(RISOrderInfoVO::getRISRReportDate).reversed())
                    .collect(Collectors.toList());

            //总记录条数,分页
            int total = collectByDate.size();
            List<RISOrderInfoVO> collect = collectByDate.stream()
                    .skip((pageNum - 1) * pageSize)
                    .limit(pageSize)
                    .collect(Collectors.toList());
            pageInfo.setList(collect);
            return pageInfo;

        }
        if (StringUtils.isBlank(risrReportDate) && StringUtils.isNotBlank(risrReportDocDesc)) {
            List<RISOrderInfoVO> collectByDoc = orderInfoVOSOut.stream()
                    .filter(ris -> StringUtils.isNotBlank(ris.getRISRReportDocDesc()))
                    .filter(or -> or.getRISRReportDocDesc().contains(risrReportDocDesc))
                    .collect(Collectors.toList());
            List<RISOrderInfoVO> collectBlank = collectByDoc.stream()
                    .filter(ris -> StringUtils.isBlank(ris.getRISRReportDate()))
                    .collect(Collectors.toList());
            List<RISOrderInfoVO> collectNotBlank = collectByDoc.stream()
                    .filter(ris -> StringUtils.isNotBlank(ris.getRISRReportDate()))
                    .sorted(Comparator.comparing(RISOrderInfoVO::getRISRReportDate).reversed())
                    .collect(Collectors.toList());
            collectBlank.addAll(collectNotBlank);
            //总记录条数
            int total = collectBlank.size();
            List<RISOrderInfoVO> collect = collectBlank.stream()
                    .skip((pageNum - 1) * pageSize)
                    .limit(pageSize)
                    .collect(Collectors.toList());
            pageInfo.setList(collect);
            return pageInfo;
        }
        if (StringUtils.isNotBlank(risrReportDate) && StringUtils.isNotBlank(risrReportDocDesc)) {
            List<RISOrderInfoVO> collectByDate = orderInfoVOSOut.stream()
                    .filter(ris -> StringUtils.isNotBlank(ris.getRISRReportDate()))
                    .filter(or -> or.getRISRReportDate().contains(risrReportDate))
                    .filter(or -> or.getRISRReportDocDesc().contains(risrReportDocDesc))
                    .sorted(Comparator.comparing(RISOrderInfoVO::getRISRReportDate).reversed())
                    .collect(Collectors.toList());
            //总记录条数
            int total = collectByDate.size();
            List<RISOrderInfoVO> collect = collectByDate.stream()
                    .skip((pageNum - 1) * pageSize)
                    .limit(pageSize)
                    .collect(Collectors.toList());
            pageInfo.setList(collect);
            return pageInfo;
        }
        List<RISOrderInfoVO> collectBlank = orderInfoVOSOut.stream()
                .filter(ris -> StringUtils.isBlank(ris.getRISRReportDate()))
                .collect(Collectors.toList());
        List<RISOrderInfoVO> collectNotBlank = orderInfoVOSOut.stream()
                .filter(ris -> StringUtils.isNotBlank(ris.getRISRReportDate()))
                .sorted(Comparator.comparing(RISOrderInfoVO::getRISRReportDate).reversed())
                .collect(Collectors.toList());
        collectBlank.addAll(collectNotBlank);
        //总记录条数
        int total = collectBlank.size();
        List<RISOrderInfoVO> collect = collectBlank.stream()
                .skip((pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
        pageInfo.setList(collect);
        return pageInfo;
    }


    @Override
    public PageInfo<RisCountVO> selectRISCount(Params paramsobject, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(1, 0, true, true, true);
        List<RisCountVO> risCountVOS = risOrderMapper.selectRISCountByCondition(paramsobject);
        PageInfo<RisCountVO> pageInfo = new PageInfo<RisCountVO>(risCountVOS);
        return pageInfo;
    }

    @Override
    public List<RISOrderInfoVO> getAllRisOrderData(List<RISOrderInfoVO> risOrderInfoVOS) {
        List<RISOrderInfoVO> orderInfoVOSOut = new ArrayList<RISOrderInfoVO>();
        try {
            List risrexamidList = new ArrayList();
            for (int i = 0; i < risOrderInfoVOS.size(); i++) {

                RISOrderInfoVO risOrderInfoVO = risOrderInfoVOS.get(i);
                String risrexamid = risOrderInfoVO.getRreidexamid();
                risrexamidList.add(risrexamid);
                String itmMastCode = risOrderInfoVO.getOEORIARCItmMastCode();
                if (StringUtils.isNotBlank(itmMastCode)) {
                    risOrderInfoVO.setOEORIARCItmMastDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_arcitmmast", itmMastCode));
                }
                String RISRReportDeptCode = risOrderInfoVO.getRISRReportDeptCode();
                if (StringUtils.isNotBlank(RISRReportDeptCode)) {
                    risOrderInfoVO.setRISRReportDeptDesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_dept", RISRReportDeptCode));
                }
                orderInfoVOSOut.add(i, risOrderInfoVO);

            }
            List<RISOrderInfoVO> RISReportlist = new ArrayList<>();
            if (risrexamidList.size()>0) {
                 RISReportlist = risOrderMapper.selectRISReportByCondition(risrexamidList);
            }
            for (int i = 0; i < RISReportlist.size(); i++) {
                RISOrderInfoVO RISReportlistOut = RISReportlist.get(i);
                String risrexamid = RISReportlistOut.getRisrexamid();
                for (int j = 0; j < risOrderInfoVOS.size(); j++) {
                    RISOrderInfoVO risOrderInfoVOSs = risOrderInfoVOS.get(j);
                    String rreidexamid = risOrderInfoVOSs.getRreidexamid();
                    if (rreidexamid.equals(risrexamid)) {
                        risOrderInfoVOSs.setReportStatus("已审核");
                        risOrderInfoVOSs.setRISRReportID(RISReportlistOut.getRISRReportID());
                        risOrderInfoVOSs.setRISRCheckDate(RISReportlistOut.getRISRCheckDate());
                        risOrderInfoVOSs.setRISRCheckTime(RISReportlistOut.getRISRCheckTime());
                        risOrderInfoVOSs.setRISRDiagDesc(RISReportlistOut.getRISRDiagDesc());
                        risOrderInfoVOSs.setRISRExamDesc(RISReportlistOut.getRISRExamDesc());
                        risOrderInfoVOSs.setRISRCheckDocDesc(RISReportlistOut.getRISRCheckDocDesc());
                        risOrderInfoVOSs.setRISMethod(RISReportlistOut.getRISMethod());
                        risOrderInfoVOSs.setRISRReportDocDesc(RISReportlistOut.getRISRReportDocDesc());
                        risOrderInfoVOSs.setRISRReportDate(RISReportlistOut.getRISRReportDate());
                    }
                    if (StringUtils.isBlank(risOrderInfoVOSs.getRISRReportID())) {
                        risOrderInfoVOSs.setReportStatus("已登记");
                    }

                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderInfoVOSOut;
    }
}

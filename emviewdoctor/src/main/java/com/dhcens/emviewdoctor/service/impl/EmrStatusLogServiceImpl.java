package com.dhcens.emviewdoctor.service.impl;

import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.EmrStatusLogVO;
import com.dhcens.emviewdoctor.mapper.EmrStatusLogMapper;
import com.dhcens.emviewdoctor.service.DictToRedisService;
import com.dhcens.emviewdoctor.service.EmrStatusLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * @author lipengfei <184441376@qq.com><br/>
 * @date 2020/4/10
 */
@Service
public class EmrStatusLogServiceImpl implements EmrStatusLogService {
    @Resource
    private EmrStatusLogMapper emrStatusLogMapper;
    @Resource
    private DictToRedisService dictToRedisService;
    @Override
    public PageInfo<EmrStatusLogVO> selectEmrStatuLog(Params paramsobject, Integer pageNum, Integer pageSize) {
        if ((pageNum!=null)&&(pageSize!=null))
        {
            PageHelper.startPage(pageNum, pageSize);
        }else {
            PageHelper.startPage(1, 0, true, true, true);
        }
        List<EmrStatusLogVO> emrStatusLogVOS=emrStatusLogMapper.selectDEmrStatusLogByCondition(paramsobject);
        PageInfo<EmrStatusLogVO> pageInfo = new PageInfo<EmrStatusLogVO>(emrStatusLogVOS);
        List<EmrStatusLogVO> emrStatusLogVOSOut=this.getAllEmrStatuLogData(pageInfo.getList());
        pageInfo.setList(emrStatusLogVOSOut);
        return pageInfo;
    }

    @Override
    public List<EmrStatusLogVO> getAllEmrStatuLogData(List<EmrStatusLogVO> emrStatusLogVOS) {
        List<EmrStatusLogVO> emrStatusLogVOSOut=new ArrayList<EmrStatusLogVO>();
        try{
            for (int i=0;i<emrStatusLogVOS.size();i++){
                EmrStatusLogVO emrStatusLogVO = emrStatusLogVOS.get(i);
                String emrstatuscode = emrStatusLogVO.getEmrstatuscode();
                //String emrstatusdesc = emrStatusLogVO.getEmrstatusdesc();
                if (StringUtils.isNotBlank(emrstatuscode))
                {
                    emrStatusLogVO.setEmrstatusdesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_status",emrstatuscode));
                }
                emrStatusLogVOSOut.add(i,emrStatusLogVO);

            }
        return emrStatusLogVOSOut;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

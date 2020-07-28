package com.dhcens.emviewdoctor.service.impl;

import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.OrdexecVO;
import com.dhcens.emviewdoctor.exception.ExceptionEnum;
import com.dhcens.emviewdoctor.exception.PlException;
import com.dhcens.emviewdoctor.mapper.OrdexecMapper;
import com.dhcens.emviewdoctor.service.DictToRedisService;
import com.dhcens.emviewdoctor.service.OrdexecService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * @author lipengfei <184441376@qq.com><br/>
 * @date 2020/1/17
 */
@Service
public class OrdexecServiceImpl implements OrdexecService {
    @Autowired
    private OrdexecMapper ordexecMapper;
    @Resource
    DictToRedisService dictToRedisService;
    @Override
    public PageInfo<OrdexecVO> selectOrdexec(Params paramsobject, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (StringUtils.isBlank(paramsobject.getOeorihosorderid())&& StringUtils.isBlank(paramsobject.getOeoreorderitemid())){
            throw new PlException(ExceptionEnum.INVALID_PARAM);
        }
        List<OrdexecVO> ordexecVOS = ordexecMapper.selectOrdexecByCondition(paramsobject);
        PageInfo<OrdexecVO> pageInfo=new PageInfo<OrdexecVO>(ordexecVOS);
        List<OrdexecVO> ordexecVOSOut=this.getAllOrdexecVOData(pageInfo.getList());
        pageInfo.setList(ordexecVOSOut);
        return pageInfo;
    }

    @Override
    public List<OrdexecVO> getAllOrdexecVOData(List<OrdexecVO> ordexecVOS) {
        List<OrdexecVO> ordexecVOSOut=new ArrayList<OrdexecVO>();
        try{
            for (int i=0;i<ordexecVOS.size();i++){
                OrdexecVO ordexecVO = ordexecVOS.get(i);
                String oeoreexecusercode = ordexecVO.getOeoreexecusercode();
                if(StringUtils.isNotBlank(oeoreexecusercode)){
                    ordexecVO.setOeoreexecuserdesc(dictToRedisService.selectDescValueByTableNameAndCode("ct_careprov",oeoreexecusercode));
                }

            ordexecVOSOut.add(i,ordexecVO);
            }
            return ordexecVOSOut;

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

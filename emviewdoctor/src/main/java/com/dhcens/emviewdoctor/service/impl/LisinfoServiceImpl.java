package com.dhcens.emviewdoctor.service.impl;

import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.LisItemresultVO;
import com.dhcens.emviewdoctor.mapper.LisItemresultVOMapper;
import com.dhcens.emviewdoctor.service.LisinfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

/**
 *
 * @author zhibao
 */
@Service
public class LisinfoServiceImpl implements LisinfoService {


    @Resource
    LisItemresultVOMapper lisItemresultVOMapper;

    @Override
    public PageInfo<LisItemresultVO> selectLisItemResult(Params paramsobject, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<LisItemresultVO> lisItemresultVOS =lisItemresultVOMapper.selectLisItemresult(paramsobject);
        List<LisItemresultVO> lisItemresultVoOut = dealMicItemCode(lisItemresultVOS);
        PageInfo<LisItemresultVO> pageInfo = new PageInfo<>(lisItemresultVoOut);

        return pageInfo;
    }

    private List<LisItemresultVO> dealMicItemCode(List<LisItemresultVO> lisItemresultVOList)
    {
        List<LisItemresultVO> lisItemresultVoOut=new ArrayList<LisItemresultVO>();
        try {
            for (int i = 0; i <lisItemresultVOList.size() ; i++) {
                LisItemresultVO lisItemresultVo = lisItemresultVOList.get(i);
                String lisrrIsmcroorganism = lisItemresultVo.getLisrrIsmcroorganism();
                String itemDesc = lisItemresultVo.getLisirItemdesc();
                if (lisrrIsmcroorganism=="1")
                {

                    lisItemresultVo.setLisirItemdesc("细菌"+itemDesc);

                }
                String lisirTestdate = lisItemresultVo.getLisirTestdate();
                if (StringUtils.isNotBlank(lisirTestdate))
                {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmdd");
                    Date tempDate = simpleDateFormat.parse(lisirTestdate);

                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
                    String tempStr = simpleDateFormat1.format(tempDate);
                    lisItemresultVo.setLisirTestdate(tempStr);
                }

                lisItemresultVoOut.add(lisItemresultVo);
            }
            return lisItemresultVoOut;
        }catch (Exception e){
            e.printStackTrace();

        }
        return lisItemresultVoOut;

    }


}

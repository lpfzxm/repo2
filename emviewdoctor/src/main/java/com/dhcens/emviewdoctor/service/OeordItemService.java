package com.dhcens.emviewdoctor.service;

import com.dhcens.emviewdoctor.entity.RT.Params;
import com.dhcens.emviewdoctor.entity.VO.OeordItemVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OeordItemService {


    /**
     * 查询sql结果集合
     * @return 医嘱信息集合
     */
    PageInfo<OeordItemVO> selectOeordItemByCondiction(Params paramsobject, Integer pageNum, Integer pageSize);

    /**
     * 根据sql查询出来的结果，遍历查询Redis缓存中的相关描述信息
     * @param oeordItemVoList sql查询出的结果集合
     * @return 带描述信息的医嘱信息集合
     */
    List<OeordItemVO> getAllOeordItem(List<OeordItemVO> oeordItemVoList);

}

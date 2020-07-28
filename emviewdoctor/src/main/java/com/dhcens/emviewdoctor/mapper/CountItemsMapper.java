package com.dhcens.emviewdoctor.mapper;


import com.dhcens.emviewdoctor.entity.RT.Params;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author zhibao
 */
public interface CountItemsMapper {


    /**
     *  查询诊断总数
     * @param paramsobject 入参对象
     * @return 数字集合
     */
    List<Integer> selectDiagnosecount(@Param("paramsobject") Params paramsobject);
    /**
     *  查询医嘱总数
     * @param paramsobject 入参对象
     * @return 数字集合
     */
    List<Integer> selectOeordItemcount(@Param("paramsobject") Params paramsobject);
    /**
     *  查询检查报告总数
     * @param paramsobject 入参对象
     * @return 数字集合
     */
    List<Integer> selectRisReportcount(@Param("paramsobject") Params paramsobject);
    /**
     *  查询检验报告总数
     * @param paramsobject 入参对象
     * @return 数字集合
     */
    List<Integer> selectLisReportcount(@Param("paramsobject") Params paramsobject);
    /**
     *  查询用药清单总数
     * @param paramsobject 入参对象
     * @return 数字集合
     */
    List<Integer> selectMedicalDetialcount(@Param("paramsobject") Params paramsobject);
    




}

package com.dhcens.emviewdoctor.entity.RT;

import java.util.List;

import lombok.Data;

/**
 * @ClassName Params
 * @Description: TODO
 * @Author lipengfei
 * @Date 2019/12/23
 * @Version V1.0
 **/

@Data
public class Params {

    /**
     * 平台就诊号
     */
    private String hdcEncId;
    private List<String> hdcEncIdIndex;
    /**
     * 平台登记号
     */
    private String hdcPatRegNo;
    /**
     * 院区ID
     */
    private String encHosCode;
    /**
     * his登记号
     */
    private String hosPatRegNo;
    /**
     * his就诊号
     */
    private String hosEncId;
    private List<String> hosEncIdIndex;
    /**
     * 区域号
     */
    private String busiFieldCode;
    /**
     * 医嘱节点
     */
    private OEORIItemInfoRT orderInfo;
    /**
     * 就诊节点
     */
    private PAAdmInfoRT encounterInfo;
    /**
     * 诊断节点
     */
    private PADDiagInfoRT diagnoseInfo;
    /**
     * 检验节点
     */
    private InspOrdInfoRT inspOrdInfo;
    /**
     * 生命体征节点
     */
    private ObserInfoRT obserInfo;
    /**
     * 检查节点
     */
    private ExamInfoRT examInfo;

}

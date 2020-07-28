package com.dhcens.emviewdoctor.entity.RT;

import java.util.List;

import lombok.Data;

/**
 * @ClassName OEORIItemInfo
 * @Description: 医嘱节点入参
 * @Author lipengfei
 * @Date 2019/12/24
 * @Version V1.0
 **/

@Data
public class OEORIItemInfoRT {

    /**
     * 医嘱项目
     */
    private String orderName;
    /**
     * 医嘱状态
     */
    private String ordStatusCode;
    /**
     * 医嘱开立时间
     */
    private String ordEnterDate;
    /**
     * 医嘱类型代码
     */
    private List<String> ordPriCode;
    /**
     * 医嘱大类代码
     */
    private List<String> ordCatCode;
    /**
     * 医嘱子类代码
     */
    private List<String> ordSubCatCode;
    /**
     * 医嘱开立科室代码
     */
    private List<String> ordDeptCode;


}

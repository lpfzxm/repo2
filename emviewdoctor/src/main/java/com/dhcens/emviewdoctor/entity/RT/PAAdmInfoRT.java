package com.dhcens.emviewdoctor.entity.RT;

import java.util.List;

import lombok.Data;

/**
 * @ClassName PAAdmInfo
 * @Description: 就诊节入参
 * @Author lipengfei
 * @Date 2019/12/26
 * @Version V1.0
 **/
@Data
public class PAAdmInfoRT {
    /**
     * 就诊开始时间
     */
    private String encStartDate;
    /**
     * 就诊开始时间
     */
    private String encEndDate;
    /**
     * 就诊类型代码
     */
    private List<String> encTypeCode;
    /**
     * 接诊医生
     */
    private List<String> encDocCode;
    /**
     * 就诊科室代码
     */
    private List<String> encDeptCode;

}

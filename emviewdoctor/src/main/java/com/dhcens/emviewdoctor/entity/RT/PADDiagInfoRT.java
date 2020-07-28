package com.dhcens.emviewdoctor.entity.RT;

import lombok.Data;

/**
 * @ClassName PADDiagInfo
 * @Description: 诊断接节点参数
 * @Author lipengfei
 * @Date 2019/12/27
 * @Version V1.0
 **/
@Data
public class PADDiagInfoRT {

    /**
     * 诊断描述
     */
    private String diagnoseName;
    /**
     * 诊断日期
     */
    private String diagnoseDate;
}

package com.dhcens.emviewdoctor.entity.RT;

import lombok.Data;

/**
 * @author lipengfei <184441376@qq.com><br/>
 * @date 2020/4/16
 * desc:检验节点参数
 */
@Data
public class InspOrdInfoRT {
    /**
     * 医嘱项描述
     */
    private String  orderName;
    /**
     * 审核日期
     */
    private String inspVerifyDate;

}

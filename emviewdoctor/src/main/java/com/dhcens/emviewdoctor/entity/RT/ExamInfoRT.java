package com.dhcens.emviewdoctor.entity.RT;

import lombok.Data;

/**
 * @author lipengfei <184441376@qq.com><br/>
 * @date 2020/4/17
 */
@Data
public class ExamInfoRT {
    /**
     * 报告医生
     */
    private String examRptDocCode;
    /**
     * 检查项目名称
     */
    private String examItemName;
    /**
     * 报告日期
     */
    private String examRptDate;
}

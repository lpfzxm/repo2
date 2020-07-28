package com.dhcens.emviewdoctor.entity.VO;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 *
 * @author zhibao
 */
@Data
public class TimeLineDiagnoseVO {
    @JSONField(name="PADDiagRowId")
    private String padDiagRowId;
    @JSONField(name="PAADMVisitNumber")
    private String padVisitNumber;
    @JSONField(name="PADDiagCode")
    private String padDiagCode;
    @JSONField(name="PADDiagDesc")
    private String padDiagDesc;
    @JSONField(name="PADDiagTypeCode")
    private String padDiagTypeCode;
    @JSONField(name="PADDiagCategoryCode")
    private String padDiagCategoryCode;
    @JSONField(name="PADDiagSeq")
    private String padDiagSeq;
    @JSONField(name="PADDiagDocCode")
    private String padDiagDocCode;
    @JSONField(name="PADDiagDocDesc")
    private String padDiagDocDesc;
    @JSONField(name="PADDiagDate")
    private String padDiagDate;
    @JSONField(name="PADDiagTime")
    private String padDiagTime;
    @JSONField(name="PADRemarks")
    private String padRemarks;
    @JSONField(name="PADHosDiagRowId")
    private String padHosDiagRowId;
}

package com.dhcens.emviewdoctor.entity.VO;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

import lombok.Data;

/**
 * @author zhibao
 */
@Data
public class TimeLineAdmVO {
    @JSONField(name="PAADMPatientID")
    private String paadmPatientId;
    @JSONField(name="PAADMHosPatientID")
    private String paadmHosPatientId;
    @JSONField(name="PAADMVisitNumber")
    private String paadmVisitNumber;
    @JSONField(name="PAADMHosVisitNumber")
    private String paadmHosVisitNumber;
    @JSONField(name="PAADMEncounterTypeCode")
    private String paadmEncounterTypeCode;
    @JSONField(name="PAADMEncounterTypeDesc")
    private String paadmEncounterTypeDesc;
    @JSONField(name="PAADMStartDate")
    private String paadmStartDate;
    @JSONField(name="PAADMStartTime")
    private String paadmStartTime;
    @JSONField(name="PAADMEndDate")
    private String paadmEndDate;
    @JSONField(name="PAADMEndTime")
    private String paadmEndTime;
    @JSONField(name="PAADMAdmitDocCode")
    private String paadmAdmitDocCode;
    @JSONField(name="PAADMAdmitDocDesc")
    private String paadmAdmitDocDesc;
    @JSONField(name="PAADMAttendDocCode")
    private String paadmAttendDocCode;
    @JSONField(name="PAADMAttendDocDesc")
    private String paadmAttendDocDesc;
    @JSONField(name="PAADMAdmitReason")
    private String paadmAdmitReason;
    @JSONField(name="PAADMAdmDeptCode")
    private String paadmAdmDeptCode;
    @JSONField(name="PAADMAdmDeptDesc")
    private String paadmAdmDeptDesc;
    @JSONField(name="PAADMDisDeptCode")
    private String paadmDisDeptCode;
    @JSONField(name="PAADMDisDeptDesc")
    private String paadmDisDeptDesc;
    @JSONField(name="PAADMStatusCode")
    private String paadmStatusCode;
    @JSONField(name="PAADMStatusDesc")
    private String paadmStatusDesc;
    @JSONField(name="PAADMCurDeptCode")
    private String paadmCurDeptCode;
    @JSONField(name="BusinessFieldCode")
    private String paadmCurDeptDesc;
    @JSONField(name="PAADMCurwardCode")
    private String paadmCurwardCode;
    @JSONField(name="PAADMCurwardDesc")
    private String paadmCurwardDesc;
    @JSONField(name="PAADMCurRoomCode")
    private String paadmCurRoomCode;
    @JSONField(name="PAADMCurRoomDesc")
    private String paadmCurRoomDesc;
    @JSONField(name="PAADMCurBedNo")
    private String paadmCurBedNo;
    @JSONField(name="PAADMDisStatusCode")
    private String paadmDisStatusCode;
    @JSONField(name="PAADMDisStatusDesc")
    private String paadmDisStatusDesc;
    @JSONField(name="PAADMCostCategoryCode")
    private String paadmCostCategoryCode;
    @JSONField(name="PAADMCostCategoryDesc")
    private String paadmCostCategoryDesc;
    @JSONField(name="PAADMDocumentNO")
    private String paadmDocumentNo;
    private List<TimeLineDiagnoseVO> diaginfo;





}

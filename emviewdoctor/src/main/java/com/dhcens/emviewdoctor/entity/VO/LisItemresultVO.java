package com.dhcens.emviewdoctor.entity.VO;

import lombok.Data;

@Data
public class LisItemresultVO {
    private Integer inspectionId;
    private Integer inspRptId;
    private String inspItemCode;
    private String inspItemDesc;
    private String inspectionValue;
    private String inspResultUnitCode;
    private String inspectionResult;
    private String inspResultDesc;
    private String inspAbnoFlag;
    private String inspResultRange;
    private String inspectionMethod;
    private String inspectionEquipment;
    private String inspDocCode;
    private String inspDocName;
    private Integer inspResultSeqNo;
    private String inspectionDate;
    private String inspectionTime;
    private String bacteriumQuantity;
    private String inspResultRemarks;
    private String hosPatRegNo;
    private String hdcPatRegNo;
    private String inspItemAbbr;
    private String inspRptMcorgFlag;
    private String hosEncId;
    private String hdcEncId;
    private String hdcOrdId;
    private String hosOrdId;
    private String busiFieldCode;
    private String encHosCode;
    private String encHosDesc;
    private String inspItemNumber;
    private String inspExtraResult;

}

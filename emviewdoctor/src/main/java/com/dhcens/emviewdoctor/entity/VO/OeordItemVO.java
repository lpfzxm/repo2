package com.dhcens.emviewdoctor.entity.VO;

import lombok.Data;

/**
 * 患者医嘱信息实体
 * author:zhibao
 */
@Data
public class OeordItemVO{
    private String hdcPatRegNo;
    private String busiFieldCode;
    private String encHosCode;
    private String encHosDesc;
    private String hosPatRegNo;
    private String hosEncId;
    private String hdcEncId;
    private String hdcOrdId;
    private String hosOrdId;
    private String medDoseFormCode;
    private String medDoseFormDesc;
    private String medicineDosage;
    private String medDosUnitCode;
    private String medDosUnitDesc;
    private String medFreqCode;
    private String medFreqDesc;
    private String medUsageCode;
    private String medUsageDesc;
    private String ordPriCode;
    private String ordPriDesc;
    private String orderRemarks;
    private String ordStopDocCode;
    private String ordStopDocName;
    private String ordDocCode;
    private String ordDocName;
    private String orderDate;
    private String orderTime;
    private String orderQuantity;
    private String medDurCode;
    private String medDurDesc;
    private String ordResultStatCode;
    private String ordResultStatDesc;
    private String ordSkinTestFlag;
    private String orderCode;
    private String orderName;
    private String ordStatusCode;
    private String ordStatusDesc;
    private String ordDeptCode;
    private String ordDeptName;
    private String ordStopDate;
    private String ordStopTime;
    private String ordReqExecDate;
    private String ordReqExecTime;
    private String ordReqExecDeptCode;
    private String ordReqExecDeptName;
    private String medPrescNo;
    private String ordGrpNo;
    private String ordCatCode;
    private String ordSubCatCode;
    private String ordParentId;
    private String ordUpdateUserCode;
}

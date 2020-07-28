package com.dhcens.emviewdoctor.entity.VO;

import lombok.Data;

/**
 * @ClassName DiagnoseVO
 * @Description: TODO
 * @Author lipengfei
 * @Date 2019/12/27
 * @Version V1.0
 **/
@Data
public class DiagnoseVO {
    private String busiFieldCode;
    private String encHosCode;
    private String encHosDesc;
    private String hdcPatRegNo;
    private String hosPatRegNo;
    private String hdcDiagId;
    private String hdcEncId;
    private String hosEncId;
    private String diagnoseCode;
    private String diagnoseName;
    private String diagTypeCode;
    private String diagTypeDesc;
    private String diagLevelCode;
    private String diagLevelDesc;
    private String diagSeqNo;
    private String diagDocCode;
    private String diagDocName;
    private String diagnoseDate;
    private String diagnoseTime;
    private String diagnoseRemarks;
    private String hosDiagId;
    private String diagUpdateUserCode;
    private String diagUpdateUserName;


}

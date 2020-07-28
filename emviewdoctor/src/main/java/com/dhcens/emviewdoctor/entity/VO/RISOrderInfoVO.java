package com.dhcens.emviewdoctor.entity.VO;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * @ClassName RISOrderInfoVO
 * @Description: TODO
 * @Author lipengfei
 * @Date 2019/12/27
 * @Version V1.0
 **/
@Data
public class RISOrderInfoVO {

    @JSONField(name = "rreidexamid")
    private String rreidexamid;
    @JSONField(name = "risrexamid")
    private String risrexamid;
    @JSONField(name = "BusinessFieldCode")
    private String BusinessFieldCode;
    @JSONField(name = "RISRReportID")
    private String RISRReportID;
    @JSONField(name = "RISRHosReportID")
    private String RISRHosReportID;
    @JSONField(name = "OEORIARCItmMastCode")
    private String OEORIARCItmMastCode;
    @JSONField(name = "OEORIARCItmMastDesc")
    private String OEORIARCItmMastDesc;
    @JSONField(name = "ReportStatus")
    private String ReportStatus;
    @JSONField(name = "RISRReportDate")
    private String RISRReportDate;
    @JSONField(name = "RISRReportDocDesc")
    private String RISRReportDocDesc;
    @JSONField(name = "RISRReportDeptCode")
    private String RISRReportDeptCode;
    @JSONField(name = "RISRReportDeptDesc")
    private String RISRReportDeptDesc;
    @JSONField(name = "RISRExamDesc")
    private String RISRExamDesc;
    @JSONField(name = "RISRDiagDesc")
    private String RISRDiagDesc;
    @JSONField(name = "RISRCheckDate")
    private String RISRCheckDate;
    @JSONField(name = "RISRCheckTime")
    private String RISRCheckTime;
    @JSONField(name = "RISMethod")
    private String RISMethod;
    @JSONField(name = "RISRCheckDocCode")
    private String RISRCheckDocCode;
    @JSONField(name = "RISRCheckDocDesc")
    private String RISRCheckDocDesc;
    @JSONField(name ="OEORIOrderItemID")
    private String OEORIOrderItemID;
    @JSONField(name ="OEORIHosOrderItemID")
    private String OEORIHosOrderItemID;
}

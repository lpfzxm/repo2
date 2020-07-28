package com.dhcens.emviewdoctor.entity.VO;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * TODO
 *
 * @author : zhibao
 */
@Data
public class PatientInfoVO {
    @JSONField(name="BusinessFieldCode")
    private String businessFieldCode;
    @JSONField(name="HospitalCode")
    private String hospitalCode;
    @JSONField(name="HospitalDesc")
    private String hospitalDesc;
    @JSONField(name="PATPatientID")
    private String patPatientId;
    @JSONField(name="PATHospPatientID")
    private String patHospPatientId;
    @JSONField(name="PATName")
    private String patName;
    @JSONField(name="PATDob")
    private String patDob;
    @JSONField(name="PATSexCode")
    private String patSexCode;
    @JSONField(name="PATSexDesc")
    private String patSexDesc;
    @JSONField(name="PATMaritalStatusCode")
    private String patMaritalStatusCode;
    @JSONField(name="PATMaritalStatusDesc")
    private String patMaritalStatusDesc;
    @JSONField(name="PATCountryCode")
    private String patCountryCode;
    @JSONField(name="PATCountryDesc")
    private String patCountryDesc;
    @JSONField(name="PATNationCode")
    private String patNationCode;
    @JSONField(name="PATNationDesc")
    private String patNationDesc;
    @JSONField(name="PATOccupationCode")
    private String patOccupationCode;
    @JSONField(name="PATOccupationDesc")
    private String patOccupationDesc;
    @JSONField(name="PATTelephone")
    private String patTelephone;
    @JSONField(name="PAPATPictureSrc")
    private String papatPictureSrc;
    @JSONField(name="PAPATDocumentNO")
    private String papatDocumentNo;
    @JSONField(name="PAPATAddress")
    private String papatAddress;
    @JSONField(name="PAPATIdCard")
    private String papatIdCard;


}

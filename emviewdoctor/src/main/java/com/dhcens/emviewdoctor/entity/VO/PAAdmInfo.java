package com.dhcens.emviewdoctor.entity.VO;

import java.util.List;

import lombok.Data;

/**
 * @ClassName PAAdmInfo
 * @Description: TODO
 * @Author lipengfei
 * @Date 2019/12/26
 * @Version V1.0
 **/
@Data
public class PAAdmInfo {


    private List<String> PAADMEncounterTypeCode;
    private List<String> PAADMAdmitDocCode;
    private List<String> PAADMAdmDeptCode;


}

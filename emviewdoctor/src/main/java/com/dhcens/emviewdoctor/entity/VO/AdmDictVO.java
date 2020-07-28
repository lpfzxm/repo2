package com.dhcens.emviewdoctor.entity.VO;

import java.util.List;

import lombok.Data;

/**
 * @ClassName AdmDictVO
 * @Description: 就诊字典类
 * @Author lipengfei
 * @Date 2020/1/3
 * @Version V1.0
 **/
@Data
public class AdmDictVO {
    private List<EncDeptListBean> encDeptList;
    private List<EncTypeListBean> encTypeList;
    private List<EncDocListBean> encDocList;
    private List<DiagnoseListBean> diagnoseList;
    @Data
    public static class EncDeptListBean {
        /**
         * PAADMAdmDeptCode : 03091091-OP09100-HX09100E-0176
         * PAADMAdmDeptDesc : 华西院区便民门诊
         */
        private String encDeptCode;
        private String encDeptName;

    }

    @Data
    public static class EncTypeListBean {
        /**
         * PAADMEncounterTypeCode : I
         * PAADMEncounterTypeDesc : 住院
         */

        private String encTypeCode;
        private String encTypeDesc;

    }

    @Data
    public static class EncDocListBean {
        /**
         * PAADMAttendDocCode : 100246
         * PAADMAttendDocDesc : 万智
         */

        private String encDocCode;
        private String encDocName;

    }

    @Data
    public static class DiagnoseListBean {
        /**
         * PADDiagCode :
         * PADDiagDesc :  ALL化疗
         */

        private String diagTypeCode;
        private String diagTypeDesc;

    }
}

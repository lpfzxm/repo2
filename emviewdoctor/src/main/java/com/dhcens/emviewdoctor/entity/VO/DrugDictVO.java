package com.dhcens.emviewdoctor.entity.VO;

import java.util.List;

import lombok.Data;

/**
 * @ClassName DrugDict
 * @Description: TODO
 * @Author lipengfei
 * @Date 2020/1/2
 * @Version V1.0
 **/
@Data
public class DrugDictVO {
        private List<OrdDeptListBean> ordDeptList;
        private List<OrdCatListBean> ordCatList;
        private List<OrdSubCatListBean> ordSubCatList;
        private List<OrdPriListBean> ordPriList;
       @Data
        public static class OrdDeptListBean {
            private String ordDeptCode;
            private String ordDeptName;
        }
        @Data
        public static class OrdCatListBean {
            private String ordCatCode;
            private String ordCatDesc;
        }

        @Data
        public static class OrdSubCatListBean {
            private String ordSubCatCode;
            private String ordSubCatDesc;
            private String ordCatCode;
        }
        @Data
        public static class OrdPriListBean {
            private String ordPriCode;
            private String ordPriDesc;
        }
}

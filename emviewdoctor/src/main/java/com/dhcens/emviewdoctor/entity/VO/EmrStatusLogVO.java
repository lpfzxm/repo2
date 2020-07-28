package com.dhcens.emviewdoctor.entity.VO;

import lombok.Data;

/**
 * @author lipengfei <184441376@qq.com><br/>
 * @date 2020/4/9
 */
@Data
public class EmrStatusLogVO {
    private String paadmvisitnumber;
    private String papatdocumentno;
    private String emrstatuscode;
    private String emrstatusdesc;
    private String emrtransoutdeptcode;
    private String emrtransoutdeptdesc;
    private String emrtransoutdatetime;
    private String emrtransoutusercode;
    private String emrtransindeptcode;
    private String emrtransindeptdesc;
    private String emrtransindatetime;
    private String emrtransinusercode;


}

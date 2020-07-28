package com.dhcens.emviewdoctor.entity.VO;

import java.io.Serializable;

import lombok.Data;

/**
 * @author lipengfei <184441376@qq.com><br/>
 * @date 2020/1/17
 */
@Data
public class OrdexecVO implements Serializable {
    private String oeoreorderitemid;
    private String oeoreexecid;
    private String oeoreexecstdate;
    private String oeoreexecsttime;
    private String oeoredoseqty;
    private String oeoredoseunitcode;
    private String oeoreqtyadmin;
    private String oeoredesc;
    private String oeorexdate;
    private String oeorextime;
    private String oeoreexecusercode;
    private String oeoreexecuserdesc;
    private String oeoredateexecuted;
    private String oeoretimeexecuted;
    private String oeoreadminstatuscode;
    private String oeorerequireexecdate;
    private String oeorerequireexectime;
    private String oeoreexecfinishdate;
    private String oeoreexecfinishtime;
    private String updatedate;
    private String updatetime;
    private String oeorehosexecid;
    private String oeorehoscode;


}

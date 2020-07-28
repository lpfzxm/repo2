package com.dhcens.emviewdoctor.entity.VO;

import java.io.Serializable;

import lombok.Data;

/**
 * @ClassName OEOrdItemIndex
 * @Description: TODO
 * @Author lipengfei
 * @Date 2019/12/2
 * @Version V1.0
 **/

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OEOrdItemIndex implements Serializable {

    private String oeoriorderid;
    private String oeoriorderchildid;
    private String oeoriorderitemid;
    private String oeoriparentorderid;
    private String oeoriarcitmmastcode;
    private String oeoriarcitmmastdesc;
    private String oeoriprioritycode;
    private String oeoriprioritydesc;
    private String oeoripriorityaddcode;
    private String oeoristatus;
    private String oeoridoseforms;
    private String oeoridoseqty;
    private String oeoridoseunitcode;
    private String oeorifreqcode;
    private String oeoriinstrcode;
    private String oeoridurationcode;
    private String oeoriorderqty;
    private String oeoriresultstatus;
    private String oeoriremarks;
    private String oeorienterdoccode;
    private String oeorienterdocdesc;
    private String oeorienterdate;
    private String oeorientertime;
    private String oeorienterdeptcode;
    private String oeorienterdeptdesc;
    private String oeoriexecdeptcode;
    private String oeoriexecdeptdesc;
    private String oeorirequireexecdate;
    private String oeorirequireexectime;
    private String oeoristopdate;
    private String oeoristoptime;
    private String oeoristopdoccode;
    private String oeoriisskintest;
    private String oeoriupdatedate;
    private String oeoriupdatetime;
    private String oeoriupdateusercode;
    private String oeoriprescno;
    private String oeorialias;
    private String oeorihosorderid;
    private String oeorihoscode;
    private String oeoriseqno;
    private String oeoricostcategorycode;
    private String oeoristopdocdesc;
    private String oeorifillerno;
    private String oeoripatientid;

}

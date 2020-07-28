package com.dhcens.emviewdoctor.entity.VO;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 *
 * @author zhibao
 */
@Data
public class CountItemsVO {

    @JSONField(name="Type")
    private String type;
    @JSONField(name = "TotalNum")
    private Integer totalnum;


}

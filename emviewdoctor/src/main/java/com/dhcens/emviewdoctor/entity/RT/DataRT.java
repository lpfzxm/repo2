package com.dhcens.emviewdoctor.entity.RT;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.UnsupportedEncodingException;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName Data
 * @Description: TODO
 * @Author lipengfei
 * @Date 2019/12/23
 * @Version V1.0
 **/

@ApiModel(value = "入参类")
public class DataRT {
    @ApiModelProperty(value = "page",example = "1")
    private Integer page;
    @ApiModelProperty(value = "rows",example = "15")
    private Integer rows;
    @ApiModelProperty(name = "params")
    private String params;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {

            this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
            this.rows = rows;

    }

    public Params getParams() {
        JSONObject jsonObject = JSON.parseObject(params);
        return JSON.parseObject(jsonObject.getString("data"), new TypeReference<Params>() {
        });
    }

    public void setParams(String params) throws UnsupportedEncodingException {
        params = new String(params.getBytes("iso-8859-1"));
        this.params = params;
    }
}

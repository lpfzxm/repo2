package com.dhcens.emviewdoctor.service;

import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "RedisWebService", // 暴露服务名称
        targetNamespace = "http://dhcc.dhcens.com/"// 命名空间,一般是接口的包名倒序
)
@Component
public interface RedisWebService {

    /**
     * 保存主数据数据
     * @param tableName 表名
     * @param paremters 入参字符串
     * @return 结果
     */
    @WebMethod
    public String saveDictToRedis(@WebParam(name = "tableName") String tableName, @WebParam(name = "paremters") String paremters);

    @WebMethod
    public boolean compareJsonStr(@WebParam(name = "themeCode") String themeCode, @WebParam(name = "jsonStr") String jsonStr);



}

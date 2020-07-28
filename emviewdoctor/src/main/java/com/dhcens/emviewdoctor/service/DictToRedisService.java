package com.dhcens.emviewdoctor.service;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface DictToRedisService {
    /**
     * 自动加载数据 到redis缓存
     * @return
     */

    Boolean autoLoadDataToRedis();

    /**
     * 根据表名 code字段名称，desc名称 查询出
     * @param tableName 表名称
     * @param codeName code 字段名称
     * @param descName 描述字段名称
     * @return 成功/失败
     */
    boolean insertTableToRedis(String tableName, String codeName, String descName);

    /**
     * 根据表名称和code值更新描述值
     * @param tableName 表名称
     * @param codeValue code值
     * @param descValue desc值
     * @return 成功/失败
     */
    boolean updateTableToRedisByCode(String tableName, String codeValue, String descValue);

    /**
     * 根据表名称查询，Redis中所有的值的Map
     * @param tableName table名称
     * @return Object
     */

    Map<Object, Object> selectAllTableByTableName(String tableName);

    /**
     * 根据表名称和code字段的值查询描述Value
     * @param tableName 表名称
     * @param codeValue  code值
     * @return desc描述
     */
    String selectDescValueByTableNameAndCode(String tableName, String codeValue);




}

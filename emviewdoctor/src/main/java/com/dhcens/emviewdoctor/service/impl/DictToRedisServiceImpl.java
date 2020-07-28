package com.dhcens.emviewdoctor.service.impl;


import com.dhcens.emviewdoctor.service.DictToRedisService;
import com.dhcens.emviewdoctor.util.JdbcPoolUtil;
import com.dhcens.emviewdoctor.util.RedisUtil;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhibao
 */
@Service
@Slf4j
public class DictToRedisServiceImpl implements DictToRedisService {


    @Resource
    Environment environment;

    @Resource
    RedisUtil redisUtil;

    @Override
    public Boolean autoLoadDataToRedis() {
        String url = environment.getProperty("spring.datasource.url");
        String password = environment.getProperty("spring.datasource.password");
        String username = environment.getProperty("spring.datasource.username");
        String driverName = environment.getProperty("spring.datasource.driver-class-name");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        boolean tsc = false;
        List<Map<String,String>> listMap = new ArrayList<>();


        String tempsql = "select table_name, schema_name, code_name, desc_name, id from hdc_user.redis_config";
        try {
            connection = JdbcPoolUtil.getConnection(driverName, url, username, password);
            preparedStatement = connection.prepareStatement(tempsql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Map<String,String> map = new HashMap<>();
                map.put("table_name",resultSet.getString("table_name"));
                map.put("schema_name",resultSet.getString("schema_name"));
                map.put("code_name",resultSet.getString("code_name"));
                map.put("desc_name",resultSet.getString("desc_name"));
                map.put("id",resultSet.getString("id"));
                listMap.add(map);
            }
            for (int i = 0; i <listMap.size() ; i++) {

                Map<String,String> newmap = listMap.get(i);
                String tablename =  newmap.get("table_name");
                String codeName =  newmap.get("code_name");
                String descName =  newmap.get("desc_name");
                tsc = this.insertTableToRedis(tablename, codeName, descName);
            }
            return tsc;
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            JdbcPoolUtil.release(resultSet, preparedStatement, connection);

        }
        return null;
    }
    @Override
    public boolean insertTableToRedis(String tableName, String codeName, String descName) {
        String url = environment.getProperty("spring.datasource.url");
        String password = environment.getProperty("spring.datasource.password");
        String username = environment.getProperty("spring.datasource.username");
        String driverName = environment.getProperty("spring.datasource.driver-class-name");
        String schemaname = "hdc_user";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int flag = 0;

        String tmpstr = "select " + codeName + "," + descName + " from " + schemaname + "." + tableName;
        try {
            connection = JdbcPoolUtil.getConnection(driverName, url, username, password);
            preparedStatement = connection.prepareStatement(tmpstr);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String code = resultSet.getString(codeName);
                String desc = resultSet.getString(descName);
                boolean tsc = redisUtil.hset(tableName, code, desc);
                if (!tsc) {
                    flag = -1;
                }
            }
            if (flag == 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {

           e.printStackTrace();

        }finally {
            JdbcPoolUtil.release(resultSet, preparedStatement, connection);
        }
        return false;
    }
    @Override
    public boolean updateTableToRedisByCode(String tableName, String codeValue, String descValue) {
        return false;
    }

    @Override
    public Map<Object, Object> selectAllTableByTableName(String tableName) {
        Map<Object, Object> map;
        map = redisUtil.hmget(tableName);
        return map;
    }
    @Override
    public String selectDescValueByTableNameAndCode(String tableName, String codeValue) {
        Object obj;
        obj = redisUtil.hget(tableName, codeValue);
        if (obj==null)
        {
            return null;
        }else {
            return obj.toString();
        }
    }
}

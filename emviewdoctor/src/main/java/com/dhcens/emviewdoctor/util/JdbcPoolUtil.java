package com.dhcens.emviewdoctor.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcPoolUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(JdbcPoolUtil.class);

    /**
     * 调用示例：
     * Connection conn = JdbcPool.getConnection("com.intersys.jdbc.CacheDriver",
     * "jdbc:Cache://192.178.61.153:1972/dmp", "dmp", "dmp");
     *
     * @param driver   驱动
     * @param url      地址
     * @param user     用户名
     * @param password 密码
     * @return 连接对象
     */
    public static Connection getConnection(String driver, String url,
                                           String user, String password) {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception cnfe) {
            cnfe.printStackTrace();
        }
        return conn;
    }

    /**
     * 释放资源
     *
     * @param rs   结果集
     * @param stmt 声明
     * @param conn 连接
     */
    public static void release(ResultSet rs, Statement stmt, Connection conn) {
        release(rs);
        release(stmt);
        release(conn);
    }

    /**
     * 释放资源
     *
     * @param o 对象
     */
    private static void release(Object o) {
        if (o == null) {
            return;
        }
        if (o instanceof ResultSet) {
            try {
                ((ResultSet) o).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (o instanceof Statement) {
            try {
                ((Statement) o).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (o instanceof Connection) {
            Connection c = (Connection) o;
            try {
                if (!c.isClosed()) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

package com.mytlx.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author TLX
 * @date 2019.11.8
 * @time 22:47
 */
public class JDBCUtil {
    private static String driverClass = null;
    private static String url = null;
    private static String name = null;
    private static String password = null;

    static{
        try {
            Properties properties = new Properties();

            InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(is);

            driverClass = properties.getProperty("driverClass");
            url = properties.getProperty("url");
            name = properties.getProperty("name");
            password = properties.getProperty("password");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象
     * @return
     */
    public static Connection getConnection() {
        Connection connection  = null;
        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(url, name, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 释放资源
     * @param rs
     * @param st
     * @param conn
     */
    public static void release(ResultSet rs, Statement st, Connection conn) {
        closeRs(rs);
        closeSt(st);
        closeConn(conn);
    }

    public static void release(Statement st, Connection conn) {
        closeSt(st);
        closeConn(conn);
    }

    private static void closeRs(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            rs = null;
        }
    }

    private static void closeSt(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            st = null;
        }
    }

    private static void closeConn(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            conn = null;
        }
    }
}

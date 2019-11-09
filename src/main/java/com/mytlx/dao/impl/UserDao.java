package com.mytlx.dao.impl;

import com.mytlx.dao.IUserDao;
import com.mytlx.domain.User;
import com.mytlx.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author TLX
 * @date 2019.11.8
 * @time 18:14
 */
public class UserDao implements IUserDao {

    @Override
    public User login(User user) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 1. 得到连接对象
            connection = JDBCUtil.getConnection();

            // 这条语句要配置数据库语法
            String sql = "select * from t_user where username=? and password=?";

            // 2. 创建ps对象
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());

            // 3. 开始执行
            rs = ps.executeQuery();

            User ret = new User();
            if (rs.next()) {
                ret.setUsername(rs.getString("username"));
                ret.setPassword(rs.getString("password"));
                ret.setId(rs.getInt("id"));
            } else {
                return null;
            }
            return ret;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(rs, ps, connection);
        }
        return null;
    }
}

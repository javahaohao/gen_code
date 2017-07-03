package com.github.javahao.db;

import com.github.javahao.config.CoreConfig;
import com.github.javahao.entity.Dialog;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * usedfor：数据库连接
 * Created by javahao on 2017/7/1.
 * auth：JavaHao
 */
public class Connection {
    private static Connection conn = new Connection();
    private java.sql.Connection connection;

    {
        Dialog dialog = CoreConfig.getDialog();
        try {
            Class.forName(dialog.getDriver());
            Properties prop = new Properties();
            prop.setProperty("user", dialog.getUsername());
            prop.setProperty("password", dialog.getPassword());
            prop.putAll(CoreConfig.getJdbcConfig());
            connection= DriverManager.getConnection(dialog.getUrl(),prop);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单例模式
     * @return 获取单例类
     */
    public static Connection getInstance(){
        return conn;
    }

    public java.sql.Connection getConnection() {
        return connection;
    }

    public void setConnection(java.sql.Connection connection) {
        this.connection = connection;
    }

    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

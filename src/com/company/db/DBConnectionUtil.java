package com.company.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBConnectionUtil {
    protected String url = "";
    protected String username = "";
    protected String password = "";


    public DBConnectionUtil() {
        super();
    }

    public DBConnectionUtil(String url, String userName, String password) {
        this.username = userName;
        this.password = password;
        this.url = url;
    }

    public Connection get() {
        Connection cnn = null;
        try {
            cnn = DriverManager.getConnection(
                    url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cnn;
    }

    public void close(Connection cnn) {
        if (cnn != null) {
            try {
                cnn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static DBConnectionUtil getCompacServerPipe(String serverType, String url, String username, String pwd) {
        DBConnectionUtil util = null;
        if ("sql-server".equalsIgnoreCase(serverType)) {
            util = new SqlServerDBConnection(url, username, pwd);
        }
        if ("mysql-server".equalsIgnoreCase(serverType)) {
            util = new MySqlDBConnection(url, username, pwd);
        }
        return util;
    }
}
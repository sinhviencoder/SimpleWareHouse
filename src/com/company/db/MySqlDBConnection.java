package com.company.db;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlDBConnection extends DBConnectionUtil {
    public static final String CONTROL_URL = "jdbc:mysql://13.76.247.254:3306/CONTROL";
    public static final String STAGING_URL = "jdbc:mysql://13.76.247.254:3306/STAGING";
    public static final String WAREHOUSE_URL = "jdbc:mysql://13.76.247.254:3306/WAREHOUSE";
    public static final String USER_NAME = "nnt09021998";
    public static final String PASSWORD = "mjsdhekoqa";
    //13.76.247.254

    public MySqlDBConnection() {
        super();
        url = CONTROL_URL;
        username = USER_NAME;
        password = PASSWORD;
    }

    public MySqlDBConnection(String url, String userName, String password) {
        super(url, userName, password);
    }

    public static void main(String... args) throws SQLException {
        DBConnectionUtil util = new MySqlDBConnection("jdbc:mysql://13.76.247.254:3306/STAGING", "nnt09021998", "mjsdhekoqa");
        util.close(util.get());

        DBConnectionUtil util2 = DBConnectionUtil.getCompacServerPipe("mysql-server", MySqlDBConnection.STAGING_URL, MySqlDBConnection.USER_NAME, MySqlDBConnection.PASSWORD);
        util2.close(util2.get());

        Connection cn = util.get();
        String query = " LOAD DATA INFILE '/home/ctc-khjl/tmp/extract/Ca2_nhom8_dulieu_20190306.csv' INTO TABLE staging_test2  FIELDS TERMINATED BY ',' LINES TERMINATED BY '\\n' IGNORE 1 ROWS (c2,c3,c4,c5,c6,c7) SET c1=1;";
        Statement stm = cn.createStatement();
        System.out.println(stm.executeUpdate(query));
    }
}



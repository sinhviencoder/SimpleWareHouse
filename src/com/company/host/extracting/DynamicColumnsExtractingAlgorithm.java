package com.company.host.extracting;

import com.company.db.DBConnectionUtil;
import com.company.db.MySqlDBConnection;
import com.company.host.HostConfiguration;
import com.company.loading.MysqlFTPConfiguratorRetriever;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DynamicColumnsExtractingAlgorithm implements ExtractAlgorithm {
    private static int DYNAMIC_COLUMN_NUMBER;
    private static final int HARD_CODE_PRIMARY_COLUMNS = 3;

    static {
        DBConnectionUtil cnUtil = new MySqlDBConnection(MySqlDBConnection.STAGING_URL, MySqlDBConnection.USER_NAME, MySqlDBConnection.PASSWORD);
        Connection cn = cnUtil.get();
        String queryColumnNumber = "SELECT COUNT(*) as columns FROM information_schema.columns WHERE table_name='staging'";
        try {
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(queryColumnNumber);
            if (rs.next()) {
                DYNAMIC_COLUMN_NUMBER = rs.getInt("columns");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int extract(HostConfiguration host, String fileName) throws SQLException {
        DBConnectionUtil cnUtil = new MySqlDBConnection(MySqlDBConnection.STAGING_URL, MySqlDBConnection.USER_NAME, MySqlDBConnection.PASSWORD);
        int insertedRecords = -1;
        String[] hostColumns = host.getHostListColumns().split(",");
        String loadInFileQuery = buildDynamicInFileDynamic(host, fileName, hostColumns.length);
//        System.out.println(loadInFileQuery);
//        System.out.println("\n\n");
        Connection cn = cnUtil.get();
        try {
            Statement stm = cn.createStatement();
            insertedRecords = stm.executeUpdate(loadInFileQuery);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (cn != null)
                cn.close();
        }
        return insertedRecords;
    }

    private String buildDynamicInFileDynamic(HostConfiguration host, String fileName, int numColumns) {
        StringBuilder bd = new StringBuilder();
        bd.append("LOAD DATA INFILE ");
        bd.append("'" + host.getLocalDir() + File.separator + fileName + "'");
        bd.append(" INTO TABLE ");
        bd.append(host.getStagingTable());
        bd.append(" FIELDS TERMINATED BY '");
        bd.append(host.getDelim() + "' ");
        bd.append("LINES TERMINATED BY '\n' ");
        bd.append("IGNORE 1 ROWS ");
        bd.append("(");
        // list dynamic mapping columns
        int requiredColumns = 0;
        for (; requiredColumns < numColumns; requiredColumns++) {
            bd.append("c" + (requiredColumns + 1) + ",");
        }
        // delete last ,
        bd.deleteCharAt(bd.length() - 1);
        bd.append(")");
        bd.append(" SET ");

        // hard_code primary key columns
        bd.append("id=null,"); // set for auto increment
        bd.append("host_id=" + host.getHostId() + ", ");
        bd.append("file_name='" + fileName + "', ");

        // end hard_code primary
        int paddingColumns = DYNAMIC_COLUMN_NUMBER - HARD_CODE_PRIMARY_COLUMNS - requiredColumns;
        for (int i = 0; i <= paddingColumns; i++) {
            bd.append("c" + (requiredColumns + i) + "=null,");
        }
        bd.deleteCharAt(bd.length() - 1);
        return bd.toString();
    }

    public static void main(String... args) throws SQLException {
        DynamicColumnsExtractingAlgorithm al = new DynamicColumnsExtractingAlgorithm();
        List<HostConfiguration> cf = new MysqlFTPConfiguratorRetriever().retrieveAll();
        for (HostConfiguration c : cf) {
            System.out.println(al.buildDynamicInFileDynamic(c, "mockup_name", c.getHostListColumns().split(",").length));
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }
}

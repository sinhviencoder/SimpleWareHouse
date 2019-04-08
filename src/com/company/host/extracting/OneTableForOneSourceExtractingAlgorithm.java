package com.company.host.extracting;

import com.company.db.DBConnectionUtil;
import com.company.db.MySqlDBConnection;
import com.company.host.HostConfiguration;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class OneTableForOneSourceExtractingAlgorithm implements ExtractAlgorithm {
	@Override
	public int extract(HostConfiguration host, String fileName, int logId) throws SQLException {
		DBConnectionUtil cnUtil = new MySqlDBConnection(MySqlDBConnection.STAGING_URL, MySqlDBConnection.USER_NAME,
				MySqlDBConnection.PASSWORD);
		// first create table if not exist
		String createIfNotExistDB = buildCreateQuery(host);

		// create dynamic ~ just kidding
		Connection cnn = cnUtil.get();
		Statement stmt = cnn.createStatement();
		stmt.executeUpdate(createIfNotExistDB);

		String fullPathFile = host.getLocalDir() + File.separator + fileName;
		String loadInFileQuery = "LOAD DATA LOCAL INFILE '" + fullPathFile + "'" + " INTO TABLE "
				+ host.getStagingTable() + " FIELDS TERMINATED BY '" + host.getDelim() + "'"
				+ " LINES TERMINATED BY '\n'" + " IGNORE 1 ROWS SET log_id=" + logId;
		// then load just like simple :)))

		int insertedRecord = -1;
		insertedRecord = stmt.executeUpdate(loadInFileQuery);
		// select so simple :)
		cnUtil.close(cnn);
		return insertedRecord;
	}

	private String buildCreateQuery(HostConfiguration host) {
		StringBuilder bd = new StringBuilder();
		String[] listColumns = host.getHostListColumns().split(",");
		bd.append("CREATE TABLE IF NOT EXISTS ");
		bd.append(host.getStagingTable());
		bd.append("(");
		
		for (int i = 0; i < listColumns.length; i++) {
			bd.append(listColumns[i] + " text,");
		}
		bd.append("log_id int");
		bd.append(")");
		bd.append(" CHARACTER SET utf8mb4");
		System.out.println(bd.toString());
		return bd.toString();
	}

}

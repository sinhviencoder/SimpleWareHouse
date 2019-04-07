package com.company.loading;

import com.company.client.ClientNotification;
import com.company.db.DBConnectionUtil;
import com.company.db.MySqlDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientNotificationRetriever {
    public List<ClientNotification> retrieveAll() throws SQLException {
        DBConnectionUtil util = new MySqlDBConnection();
        Connection cnn = util.get();

        List<ClientNotification> result = new ArrayList<ClientNotification>();
        String selectAll = "SELECT * FROM client_notification";
        PreparedStatement pre = cnn.prepareStatement(selectAll);
        ResultSet set = pre.executeQuery();
        ClientNotification notificationPoint = null;
        while (set.next()) {
            notificationPoint = new ClientNotification();
            notificationPoint.setHostId(set.getInt("host_id"));
            notificationPoint.setHostNotificationDefaultStatus(set.getString("host_notification_status"));
            notificationPoint.setMyNotificationId(set.getString("my_notification_id"));
            notificationPoint.setHostUrlConnection(set.getString("host_url_connection"));
            notificationPoint.setDbType(set.getString("db_server_type"));
            notificationPoint.setHostUserName(set.getString("host_username"));
            notificationPoint.setHostPassword(set.getString("host_password"));
            notificationPoint.setHostTableName(set.getString("host_table_name"));
            notificationPoint.setHostListColumn(set.getString("host_list_col"));
            notificationPoint.setNotificationScriptName(set.getString("notification_script_name"));
            result.add(notificationPoint);
        }

        util.close(cnn);
        return result;
    }
}

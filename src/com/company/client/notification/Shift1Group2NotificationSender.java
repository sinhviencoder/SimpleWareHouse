package com.company.client.notification;

import com.company.client.ClientNotification;
import com.company.db.DBConnectionUtil;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;

public class Shift1Group2NotificationSender implements NotificationSender {
    public int send(File f, ClientNotification notificationInformer) throws Exception {
        DBConnectionUtil utilDBConnection = DBConnectionUtil.getCompacServerPipe(notificationInformer.getDbType()
                , notificationInformer.getHostUrlConnection(), notificationInformer.getHostUserName(), notificationInformer.getHostPassword());
        if (utilDBConnection == null) {
            return 0;
        }
        Connection cn = null;
        int affected = -1;
        try {
            cn = utilDBConnection.get();
            String notificationQuery = "INSERT INTO ThaoLe.data_file_logs(id_config,file_name,status,size, time,delimiter) values(?,?,?,?,?,?)";
            PreparedStatement stmt = cn.prepareStatement(notificationQuery);
            stmt.setString(1, notificationInformer.getMyNotificationId());
            stmt.setString(2, f.getName());
            stmt.setString(3, notificationInformer.getHostNotificationDefaultStatus());
            stmt.setLong(4, f.length());
            Calendar vn = Calendar.getInstance(TimeZone.getTimeZone("Asia/Bangkok"));
            stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()), vn);
            stmt.setString(6, ";");
            affected = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("cannot sending sql notification to " + notificationInformer.getHostId());
        } finally {
            if (cn != null) {
                cn.close();
            }
        }

        return affected;
    }
}

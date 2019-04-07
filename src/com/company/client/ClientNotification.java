package com.company.client;


public class ClientNotification {
    private int hostId;
    private String hostUrlConnection, hostNotificationDefaultStatus, dbType, hostUserName, hostPassword;
    private String myNotificationId , hostTableName, hostListColumn, notificationScriptName;

    public String getHostListColumn() {
        return hostListColumn;
    }

    public void setHostListColumn(String hostListColumn) {
        this.hostListColumn = hostListColumn;
    }

    public String getNotificationScriptName() {
        return notificationScriptName;
    }

    public void setNotificationScriptName(String notificationScriptName) {
        this.notificationScriptName = notificationScriptName;
    }

    public String getHostTableName() {
        return hostTableName;
    }

    public void setHostTableName(String hostTableName) {
        this.hostTableName = hostTableName;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public ClientNotification() {

    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public String getMyNotificationId() {
        return myNotificationId;
    }

    public void setMyNotificationId(String myNotificationId) {
        this.myNotificationId = myNotificationId;
    }

    public String getHostUrlConnection() {
        return hostUrlConnection;
    }

    public void setHostUrlConnection(String hostUrlConnection) {
        this.hostUrlConnection = hostUrlConnection;
    }

    public String getHostNotificationDefaultStatus() {
        return hostNotificationDefaultStatus;
    }

    public void setHostNotificationDefaultStatus(String hostNotificationDefaultStatus) {
        this.hostNotificationDefaultStatus = hostNotificationDefaultStatus;
    }

    public String getHostUserName() {
        return hostUserName;
    }

    public void setHostUserName(String hostUserName) {
        this.hostUserName = hostUserName;
    }

    public String getHostPassword() {
        return hostPassword;
    }

    public void setHostPassword(String hostPassword) {
        this.hostPassword = hostPassword;
    }

    public String[] defautColumnNames() {
        return hostListColumn.split(";");
    }
}

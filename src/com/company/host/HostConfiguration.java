package com.company.host;

import java.util.List;

public class HostConfiguration {
    private String hostName, user, password, remoteDir, localDir, formatFile, extractScriptName, transformScriptName;
    private int port, hostId;
    private String hostListColumns, warehouseRequiredColumns, delim, stagingTable;
    private List<String> fileNames;

    public HostConfiguration() {
    }

    public String getDelim() {
        return delim;
    }

    public String getWarehouseRequiredColumns() {
        return warehouseRequiredColumns;
    }

    public void setWarehouseRequiredColumns(String warehouseRequiredColumns) {
        this.warehouseRequiredColumns = warehouseRequiredColumns;
    }

    public void setDelim(String delim) {
        this.delim = delim;
    }

    public String getStagingTable() {
        return stagingTable;
    }

    public void setStagingTable(String stagingTable) {
        this.stagingTable = stagingTable;
    }

    public String getHostListColumns() {
        return hostListColumns;
    }

    public void setHostListColumns(String hostListColumns) {
        this.hostListColumns = hostListColumns;
    }

    public String getTransformScriptName() {
        return transformScriptName;
    }

    public void setTransformScriptName(String transformScriptName) {
        this.transformScriptName = transformScriptName;
    }

    public String getExtractScriptName() {
        return extractScriptName;
    }

    public void setExtractScriptName(String extractScriptName) {
        this.extractScriptName = extractScriptName;
    }

    public List<String> getOwnerFileNames() {
        return fileNames;
    }

    public void setOwnerFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public String getFormatFile() {
        return formatFile;
    }

    public void setFormatFile(String formatFile) {
        this.formatFile = formatFile;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemoteDir() {
        return remoteDir;
    }

    public void setRemoteDir(String remoteDir) {
        this.remoteDir = remoteDir;
    }

    public String getLocalDir() {
        return localDir;
    }

    public void setLocalDir(String localDir) {
        this.localDir = localDir;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}

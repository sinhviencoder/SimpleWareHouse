package com.company.file;

public class Log {
	private int logId, hostId;
	private String fileName;

	public Log(int logId, int hostId, String fileName) {
		super();
		this.logId = logId;
		this.hostId = hostId;
		this.fileName = fileName;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public int getHostId() {
		return hostId;
	}

	public void setHostId(int hostId) {
		this.hostId = hostId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}

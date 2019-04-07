package com.company;

import com.company.client.ClientNotification;
import com.company.loading.ClientNotificationRetriever;
import com.company.client.notification.NotificationSender;
import com.company.client.notification.NotificationSenderFactory;
import org.apache.commons.net.ftp.FTPClient;
import java.io.*;
import java.sql.SQLException;
import java.util.List;

public class UploadFileToFtpServer {
	public static void main(String... args) {
		// UploadFileToFtpServer -h host_name
		// -p port_num
		// -user user
		// -pwd password
		// -src url_of_file
		// -des remote_dir
		//
		// for testing purpose
		args = new String[12];
		//

		if (args.length != 12) {
			System.out.println("Missing param, cannot proceed!");
		}
		String hostName = "", portNumber = "", userName = "", passWord = "", srcFile = "", desFile = "";
		for (int i = 0; i < args.length; i += 2) {
			String paramName = args[i];
			if ("-h".equals(paramName)) {
				hostName = args[i + 1];
			}
			if ("-p".equals(paramName)) {
				portNumber = args[i + 1];
			}
			if ("-user".equals(paramName)) {
				userName = args[i + 1];
			}
			if ("-pwd".equals(paramName)) {
				passWord = args[i + 1];
			}
			if ("-src".equals(paramName)) {
				srcFile = args[i + 1];
			}
			if ("-des".equals(paramName)) {
				desFile = args[i + 1];
			}
		}
		hostName = "13.76.247.254";
		portNumber = "21";
		userName = "guest1";
		passWord = "iamaguest";
		srcFile = "/home/ctc-khjl/tmp/ca2_nhom3_dulieu_20190306.csv";
		desFile = "./files/";
		FTPClient ftpClient = new FTPClient();
		ftpClient.setConnectTimeout(10000);
		try {
			ftpClient.connect(hostName, Integer.parseInt(portNumber));
			boolean isLogin = ftpClient.login(userName, passWord);
			if (!isLogin) {
				System.out.println("Cannot login! end processing!");
			}
			File file = new File(srcFile);
			String fileName = file.getName();
			ftpClient.enterLocalPassiveMode();
			ftpClient.changeWorkingDirectory(desFile);

			// just make sure not have old same file or old error uploaded file
			ftpClient.deleteFile(fileName);
			InputStream ipStream = new BufferedInputStream(new FileInputStream(file));
			boolean isDone = ftpClient.storeFile(fileName, ipStream);
			if (isDone) {
				System.out.println("Uploaded! file : " + fileName + " to : " + desFile + " , on: " + hostName);
				notifyToAllClient(file);
			}
			ipStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void notifyToAllClient(File file) {
		try {
			List<ClientNotification> noEndPointInfors = new ClientNotificationRetriever().retrieveAll();
			NotificationSender sender = null;
			for (ClientNotification endPointInfor : noEndPointInfors) {
				sender = NotificationSenderFactory.get(endPointInfor.getNotificationScriptName());
				if (sender == null)
					continue;
				try {
					int affected = sender.send(file, endPointInfor);
					if (affected >= 1)
						System.out.println("sending notification to " + endPointInfor.getHostId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

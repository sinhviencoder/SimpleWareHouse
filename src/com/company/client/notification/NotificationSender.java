package com.company.client.notification;

import com.company.client.ClientNotification;

import java.io.File;

public interface NotificationSender {
    int send(File f, ClientNotification notificationInformer) throws Exception;
}

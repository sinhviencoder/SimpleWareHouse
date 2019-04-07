package com.company.client.notification;

public class NotificationSenderFactory {
    public static NotificationSender get(String notificationScriptName) {
        NotificationSender sender = null;
        notificationScriptName = notificationScriptName.toLowerCase().trim();
        switch (notificationScriptName) {
            case "shift1group2":
                sender = new Shift1Group2NotificationSender();
                break;
        }
        return sender;
    }
}

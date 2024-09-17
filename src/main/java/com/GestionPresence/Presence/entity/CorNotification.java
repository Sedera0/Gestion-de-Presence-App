package com.GestionPresence.Presence.entity;

import java.util.Date;

public class CorNotification {
    private int corNotificationId;
    private String studentId;
    private Date notificationDate;
    private String reason;

    // Getters and setters
    public int getCorNotificationId() {
        return corNotificationId;
    }

    public void setCorNotificationId(int corNotificationId) {
        this.corNotificationId = corNotificationId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setDateNotification(Date dateNotification) {
        this.notificationDate = notificationDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String raison) {
        this.reason = reason;
    }
}


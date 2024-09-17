package com.GestionPresence.Presence.entity;

import java.util.Date;

public class CorNotification {
    private int corNotificationId;
    private String etudiantId;
    private Date dateNotification;
    private String raison;

    // Getters and setters
    public int getCorNotificationId() {
        return corNotificationId;
    }

    public void setCorNotificationId(int corNotificationId) {
        this.corNotificationId = corNotificationId;
    }

    public String getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(String etudiantId) {
        this.etudiantId = etudiantId;
    }

    public Date getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification(Date dateNotification) {
        this.dateNotification = dateNotification;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }
}


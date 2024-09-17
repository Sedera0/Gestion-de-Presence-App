package com.GestionPresence.Presence.entity;

import java.util.Date;

public class Presence {
    private int presenceId;
    private String etudiantId;
    private int coursId;
    private Date datePresence;
    private boolean estPresent;

    // Getters and setters
    public int getPresenceId() {
        return presenceId;
    }

    public void setPresenceId(int presenceId) {
        this.presenceId = presenceId;
    }

    public String getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(String etudiantId) {
        this.etudiantId = etudiantId;
    }

    public int getCoursId() {
        return coursId;
    }

    public void setCoursId(int coursId) {
        this.coursId = coursId;
    }

    public Date getDatePresence() {
        return datePresence;
    }

    public void setDatePresence(Date datePresence) {
        this.datePresence = datePresence;
    }

    public boolean isEstPresent() {
        return estPresent;
    }

    public void setEstPresent(boolean estPresent) {
        this.estPresent = estPresent;
    }
}


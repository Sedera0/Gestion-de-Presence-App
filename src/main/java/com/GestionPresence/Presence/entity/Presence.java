package com.GestionPresence.Presence.entity;


import java.util.Date;

public class Presence {
    private int presenceId;
    private String studentId;
    private int courseId;
    private Date presenceDate;
    private PresenceStatus status;

    public int getPresenceId() {
        return presenceId;
    }

    public void setPresenceId(int presenceId) {
        this.presenceId = presenceId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getPresenceDate() {
        return presenceDate;
    }

    public void setPresenceDate(Date presenceDate) {
        this.presenceDate = presenceDate;
    }

    public PresenceStatus getStatus() {
        return status;
    }

    public void setStatus(PresenceStatus status) {
        this.status = status;
    }
}


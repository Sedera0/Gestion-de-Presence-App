package com.GestionPresence.Presence.service;

import com.GestionPresence.Presence.entity.CorNotification;
import com.GestionPresence.Presence.repository.CorNotificationDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CorNotificationService {
    private CorNotificationDAO repository;

    public CorNotificationService(Connection connection) {
        this.repository = new CorNotificationDAO(connection);
    }

    public void addCorNotification(CorNotification corNotification) throws SQLException {
        repository.addCorNotification(corNotification);
    }

    public CorNotification getCorNotification(int corNotificationId) throws SQLException {
        return repository.getCorNotification(corNotificationId);
    }

    public List<CorNotification> getAllCorNotifications() throws SQLException {
        return repository.getAllCorNotifications();
    }

    public void updateCorNotification(CorNotification corNotification) throws SQLException {
        repository.updateCorNotification(corNotification);
    }

    public void deleteCorNotification(int corNotificationId) throws SQLException {
        repository.deleteCorNotification(corNotificationId);
    }
}

package com.GestionPresence.Presence.repository;

import com.GestionPresence.Presence.entity.CorNotification;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CorNotificationDAO {
    private Connection connection;

    public CorNotificationDAO(Connection connection) {
        this.connection = connection;
    }

    public void addCorNotification(CorNotification corNotification) throws SQLException {
        String query = "INSERT INTO cor_notification (etudiant_id, date_notification, raison) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, corNotification.getEtudiantId());
            statement.setDate(2, new java.sql.Date(corNotification.getDateNotification().getTime()));
            statement.setString(3, corNotification.getRaison());
            statement.executeUpdate();
        }
    }

    public CorNotification getCorNotification(int corNotificationId) throws SQLException {
        String query = "SELECT * FROM cor_notification WHERE cor_notification_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, corNotificationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                CorNotification corNotification = new CorNotification();
                corNotification.setCorNotificationId(resultSet.getInt("cor_notification_id"));
                corNotification.setEtudiantId(resultSet.getString("etudiant_id"));
                corNotification.setDateNotification(resultSet.getDate("date_notification"));
                corNotification.setRaison(resultSet.getString("raison"));
                return corNotification;
            }
        }
        return null;
    }

    public List<CorNotification> getAllCorNotifications() throws SQLException {
        List<CorNotification> notifications = new ArrayList<>();
        String query = "SELECT * FROM cor_notification";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                CorNotification corNotification = new CorNotification();
                corNotification.setCorNotificationId(resultSet.getInt("cor_notification_id"));
                corNotification.setEtudiantId(resultSet.getString("etudiant_id"));
                corNotification.setDateNotification(resultSet.getDate("date_notification"));
                corNotification.setRaison(resultSet.getString("raison"));
                notifications.add(corNotification);
            }
        }
        return notifications;
    }

    public void updateCorNotification(CorNotification corNotification) throws SQLException {
        String query = "UPDATE cor_notification SET etudiant_id = ?, date_notification = ?, raison = ? WHERE cor_notification_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, corNotification.getEtudiantId());
            statement.setDate(2, new java.sql.Date(corNotification.getDateNotification().getTime()));
            statement.setString(3, corNotification.getRaison());
            statement.setInt(4, corNotification.getCorNotificationId());
            statement.executeUpdate();
        }
    }

    public void deleteCorNotification(int corNotificationId) throws SQLException {
        String query = "DELETE FROM cor_notification WHERE cor_notification_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, corNotificationId);
            statement.executeUpdate();
        }
    }
}


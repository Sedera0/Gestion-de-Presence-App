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
        String query = "INSERT INTO cor_notification (student_id, notification_date, reason) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, corNotification.getStudentId());
            statement.setDate(2, new java.sql.Date(corNotification.getNotificationDate().getTime()));
            statement.setString(3, corNotification.getReason());
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
                corNotification.setStudentId(resultSet.getString("student_id"));
                corNotification.setDateNotification(resultSet.getDate("notification_date"));
                corNotification.setReason(resultSet.getString("reason"));
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
                corNotification.setStudentId(resultSet.getString("student_id"));
                corNotification.setDateNotification(resultSet.getDate("notification_date"));
                corNotification.setReason(resultSet.getString("reason"));
                notifications.add(corNotification);
            }
        }
        return notifications;
    }

    public void updateCorNotification(CorNotification corNotification) throws SQLException {
        String query = "UPDATE cor_notification SET student_id = ?, notification_date = ?, reason = ? WHERE cor_notification_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, corNotification.getStudentId());
            statement.setDate(2, new java.sql.Date(corNotification.getNotificationDate().getTime()));
            statement.setString(3, corNotification.getReason());
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


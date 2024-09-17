package com.GestionPresence.Presence.repository;

import com.GestionPresence.Presence.config.DatabaseConnection;
import com.GestionPresence.Presence.entity.Presence;
import com.GestionPresence.Presence.entity.PresenceStatus;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PresenceDAO {

    // Method to add a new presence
    public void addPresence(Presence presence) throws SQLException {
        String query = "INSERT INTO presence (student_id, course_id, presence_date, status) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, presence.getStudentId());
            statement.setInt(2, presence.getCourseId());
            statement.setDate(3, new java.sql.Date(presence.getPresenceDate().getTime()));
            statement.setString(4, presence.getStatus().name());
            statement.executeUpdate();
        }
    }

    // Method to retrieve a presence by ID
    public Presence getPresence(int presenceId) throws SQLException {
        String query = "SELECT * FROM presence WHERE presence_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, presenceId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Presence presence = new Presence();
                presence.setPresenceId(resultSet.getInt("presence_id"));
                presence.setStudentId(resultSet.getString("student_id"));
                presence.setCourseId(resultSet.getInt("course_id"));
                presence.setPresenceDate(resultSet.getDate("presence_date"));
                presence.setStatus(PresenceStatus.valueOf(resultSet.getString("status")));
                return presence;
            }
        }
        return null;
    }

    // Method to retrieve all presences
    public List<Presence> getAllPresences() throws SQLException {
        List<Presence> presences = new ArrayList<>();
        String query = "SELECT * FROM presence";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Presence presence = new Presence();
                presence.setPresenceId(resultSet.getInt("presence_id"));
                presence.setStudentId(resultSet.getString("student_id"));
                presence.setCourseId(resultSet.getInt("course_id"));
                presence.setPresenceDate(resultSet.getDate("presence_date"));
                presence.setStatus(PresenceStatus.valueOf(resultSet.getString("status")));
                presences.add(presence);
            }
        }
        return presences;
    }

    // Method to update a presence
    public void updatePresence(Presence presence) throws SQLException {
        String query = "UPDATE presence SET student_id = ?, course_id = ?, presence_date = ?, status = ? WHERE presence_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, presence.getStudentId());
            statement.setInt(2, presence.getCourseId());
            statement.setDate(3, new java.sql.Date(presence.getPresenceDate().getTime()));
            statement.setString(4, presence.getStatus().name());
            statement.setInt(5, presence.getPresenceId());
            statement.executeUpdate();
        }
    }

    // Method to delete a presence
    public void deletePresence(int presenceId) throws SQLException {
        String query = "DELETE FROM presence WHERE presence_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, presenceId);
            statement.executeUpdate();
        }
    }

    // Method to retrieve all presences by student ID
    public List<Presence> getAllPresencesByStudent(String studentId) throws SQLException {
        List<Presence> presences = new ArrayList<>();
        String query = "SELECT * FROM presence WHERE student_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Presence presence = new Presence();
                presence.setPresenceId(resultSet.getInt("presence_id"));
                presence.setStudentId(resultSet.getString("student_id"));
                presence.setCourseId(resultSet.getInt("course_id"));
                presence.setPresenceDate(resultSet.getDate("presence_date"));
                presence.setStatus(PresenceStatus.valueOf(resultSet.getString("status")));
                presences.add(presence);
            }
        }
        return presences;
    }

    // Method to update a notification message for a student
    public void updateNotificationMessage(String studentId, String message) throws SQLException {
        String query = "UPDATE student SET cor_notification = ? WHERE student_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, message);
            statement.setString(2, studentId);
            statement.executeUpdate();
        }
    }

    // Method to count unjustified absences
    public int countUnjustifiedAbsences(String studentId) throws SQLException {
        String query = "SELECT COUNT(*) FROM presence WHERE student_id = ? AND status = 'ABSENT'";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    return 0;
                }
            }
        }
    }
}

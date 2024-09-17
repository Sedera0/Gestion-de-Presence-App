package com.GestionPresence.Presence.repository;

import com.GestionPresence.Presence.entity.Presence;
import com.GestionPresence.Presence.entity.PresenceStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PresenceDAO {
    private Connection connection;

    public PresenceDAO(Connection connection) {
        this.connection = connection;
    }

    // Méthode pour ajouter une nouvelle présence
    public void addPresence(Presence presence) throws SQLException {
        String query = "INSERT INTO presence (student_id, course_id, presence_date, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, presence.getStudentId());
            statement.setInt(2, presence.getCourseId());
            statement.setDate(3, new java.sql.Date(presence.getPresenceDate().getTime()));
            statement.setString(4, presence.getStatus().name());
            statement.executeUpdate();
        }
    }

    // Méthode pour récupérer une présence par ID
    public Presence getPresence(int presenceId) throws SQLException {
        String query = "SELECT * FROM presence WHERE presence_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
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

    // Méthode pour récupérer toutes les présences
    public List<Presence> getAllPresences() throws SQLException {
        List<Presence> presences = new ArrayList<>();
        String query = "SELECT * FROM presence";
        try (Statement statement = connection.createStatement()) {
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

    // Méthode pour mettre à jour une présence
    public void updatePresence(Presence presence) throws SQLException {
        String query = "UPDATE presence SET student_id = ?, course_id = ?, presence_date = ?, status = ? WHERE presence_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, presence.getStudentId());
            statement.setInt(2, presence.getCourseId());
            statement.setDate(3, new java.sql.Date(presence.getPresenceDate().getTime()));
            statement.setString(4, presence.getStatus().name());
            statement.setInt(5, presence.getPresenceId());
            statement.executeUpdate();
        }
    }

    // Méthode pour supprimer une présence
    public void deletePresence(int presenceId) throws SQLException {
        String query = "DELETE FROM presence WHERE presence_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, presenceId);
            statement.executeUpdate();
        }
    }

    // Méthode pour récupérer toutes les présences d'un étudiant
    public List<Presence> getAllPresencesByStudent(int studentId) throws SQLException {
        String query = "SELECT * FROM presence WHERE student_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            List<Presence> presences = new ArrayList<>();
            while (resultSet.next()) {
                Presence presence = new Presence();
                presence.setPresenceId(resultSet.getInt("presence_id"));
                presence.setStudentId(resultSet.getString("student_id"));
                presence.setCourseId(resultSet.getInt("course_id"));
                presence.setPresenceDate(resultSet.getDate("presence_date"));
                presence.setStatus(PresenceStatus.valueOf(resultSet.getString("status")));
                presences.add(presence);
            }
            return presences;
        }
    }

    public void updateNotificationMessage(String studentId, String message) throws SQLException {
        String query = "UPDATE student SET cor_notification = ? WHERE student_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, message);
            statement.setString(2, studentId);
            statement.executeUpdate();
        }
    }

    public int countUnjustifiedAbsences(String studentId) throws SQLException {
        String query = "SELECT COUNT(*) FROM presence WHERE student_id = ? AND justification IS NULL";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
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

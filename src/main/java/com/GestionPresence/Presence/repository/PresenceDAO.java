package com.GestionPresence.Presence.repository;
import com.GestionPresence.Presence.entity.Presence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PresenceDAO{
    private Connection connection;

    public PresenceDAO(Connection connection) {
        this.connection = connection;
    }

    public void addPresence(Presence presence) throws SQLException {
        String query = "INSERT INTO presence (student_id, course_id, presence_date, is_present) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, presence.getStudentId());
            statement.setInt(2, presence.getCourseId());
            statement.setDate(3, new java.sql.Date(presence.getPresenceDate().getTime()));
            statement.setBoolean(4, presence.isPresent());
            statement.executeUpdate();
        }
    }

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
                presence.setPresent(resultSet.getBoolean("is_present"));
                return presence;
            }
        }
        return null;
    }

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
                presence.setPresent(resultSet.getBoolean("is_present"));
                presences.add(presence);
            }
        }
        return presences;
    }

    public void updatePresence(Presence presence) throws SQLException {
        String query = "UPDATE presence SET student_id = ?, course_id = ?, date_presence = ?, est_present = ? WHERE presence_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, presence.getStudentId());
            statement.setInt(2, presence.getCourseId());
            statement.setDate(3, new java.sql.Date(presence.getPresenceDate().getTime()));
            statement.setBoolean(4, presence.isPresent());
            statement.setInt(5, presence.getPresenceId());
            statement.executeUpdate();
        }
    }

    public void deletePresence(int presenceId) throws SQLException {
        String query = "DELETE FROM presence WHERE presence_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, presenceId);
            statement.executeUpdate();
        }
    }
}

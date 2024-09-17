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
        String query = "INSERT INTO presence (etudiant_id, cours_id, date_presence, est_present) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, presence.getEtudiantId());
            statement.setInt(2, presence.getCoursId());
            statement.setDate(3, new java.sql.Date(presence.getDatePresence().getTime()));
            statement.setBoolean(4, presence.isEstPresent());
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
                presence.setEtudiantId(resultSet.getString("etudiant_id"));
                presence.setCoursId(resultSet.getInt("cours_id"));
                presence.setDatePresence(resultSet.getDate("date_presence"));
                presence.setEstPresent(resultSet.getBoolean("est_present"));
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
                presence.setEtudiantId(resultSet.getString("etudiant_id"));
                presence.setCoursId(resultSet.getInt("cours_id"));
                presence.setDatePresence(resultSet.getDate("date_presence"));
                presence.setEstPresent(resultSet.getBoolean("est_present"));
                presences.add(presence);
            }
        }
        return presences;
    }

    public void updatePresence(Presence presence) throws SQLException {
        String query = "UPDATE presence SET etudiant_id = ?, cours_id = ?, date_presence = ?, est_present = ? WHERE presence_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, presence.getEtudiantId());
            statement.setInt(2, presence.getCoursId());
            statement.setDate(3, new java.sql.Date(presence.getDatePresence().getTime()));
            statement.setBoolean(4, presence.isEstPresent());
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

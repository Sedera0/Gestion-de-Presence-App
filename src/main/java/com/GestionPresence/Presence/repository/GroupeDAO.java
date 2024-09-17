package com.GestionPresence.Presence.repository;

import com.GestionPresence.Presence.entity.Groupe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupeDAO {
    private Connection connection;

    public GroupeDAO(Connection connection) {
        this.connection = connection;
    }

    public void addGroupe(Groupe groupe) throws SQLException {
        String query = "INSERT INTO groupe (nom_groupe) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, groupe.getNomGroupe());
            statement.executeUpdate();
        }
    }

    public Groupe getGroupe(int groupeId) throws SQLException {
        String query = "SELECT * FROM groupe WHERE groupe_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, groupeId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Groupe groupe = new Groupe();
                groupe.setGroupeId(resultSet.getInt("groupe_id"));
                groupe.setNomGroupe(resultSet.getString("nom_groupe"));
                return groupe;
            }
        }
        return null;
    }

    public List<Groupe> getAllGroupes() throws SQLException {
        List<Groupe> groupes = new ArrayList<>();
        String query = "SELECT * FROM groupe";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Groupe groupe = new Groupe();
                groupe.setGroupeId(resultSet.getInt("groupe_id"));
                groupe.setNomGroupe(resultSet.getString("nom_groupe"));
                groupes.add(groupe);
            }
        }
        return groupes;
    }

    public void updateGroupe(Groupe groupe) throws SQLException {
        String query = "UPDATE groupe SET nom_groupe = ? WHERE groupe_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, groupe.getNomGroupe());
            statement.setInt(2, groupe.getGroupeId());
            statement.executeUpdate();
        }
    }

    public void deleteGroupe(int groupeId) throws SQLException {
        String query = "DELETE FROM groupe WHERE groupe_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, groupeId);
            statement.executeUpdate();
        }
    }
}

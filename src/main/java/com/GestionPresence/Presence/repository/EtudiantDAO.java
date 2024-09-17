package com.GestionPresence.Presence.repository;

import com.GestionPresence.Presence.entity.Etudiant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDAO {
    private Connection connection;

    public EtudiantDAO(Connection connection) {
        this.connection = connection;
    }

    public void addEtudiant(Etudiant etudiant) throws SQLException {
        String query = "INSERT INTO etudiant (etudiant_id, nom, prenom, email, telephone, date_naissance) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, etudiant.getEtudiantId());
            statement.setString(2, etudiant.getNom());
            statement.setString(3, etudiant.getPrenom());
            statement.setString(4, etudiant.getEmail());
            statement.setString(5, etudiant.getTelephone());
            statement.setDate(6, new java.sql.Date(etudiant.getDateNaissance().getTime()));
            statement.executeUpdate();
        }
    }

    public Etudiant getEtudiant(String etudiantId) throws SQLException {
        String query = "SELECT * FROM etudiant WHERE etudiant_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, etudiantId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.setEtudiantId(resultSet.getString("etudiant_id"));
                etudiant.setNom(resultSet.getString("nom"));
                etudiant.setPrenom(resultSet.getString("prenom"));
                etudiant.setEmail(resultSet.getString("email"));
                etudiant.setTelephone(resultSet.getString("telephone"));
                etudiant.setDateNaissance(resultSet.getDate("date_naissance"));
                return etudiant;
            }
        }
        return null;
    }

    public List<Etudiant> getAllEtudiants() throws SQLException {
        List<Etudiant> etudiants = new ArrayList<>();
        String query = "SELECT * FROM etudiant";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.setEtudiantId(resultSet.getString("etudiant_id"));
                etudiant.setNom(resultSet.getString("nom"));
                etudiant.setPrenom(resultSet.getString("prenom"));
                etudiant.setEmail(resultSet.getString("email"));
                etudiant.setTelephone(resultSet.getString("telephone"));
                etudiant.setDateNaissance(resultSet.getDate("date_naissance"));
                etudiants.add(etudiant);
            }
        }
        return etudiants;
    }

    public void updateEtudiant(Etudiant etudiant) throws SQLException {
        String query = "UPDATE etudiant SET nom = ?, prenom = ?, email = ?, telephone = ?, date_naissance = ? WHERE etudiant_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, etudiant.getNom());
            statement.setString(2, etudiant.getPrenom());
            statement.setString(3, etudiant.getEmail());
            statement.setString(4, etudiant.getTelephone());
            statement.setDate(5, new java.sql.Date(etudiant.getDateNaissance().getTime()));
            statement.setString(6, etudiant.getEtudiantId());
            statement.executeUpdate();
        }
    }

    public void deleteEtudiant(String etudiantId) throws SQLException {
        String query = "DELETE FROM etudiant WHERE etudiant_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, etudiantId);
            statement.executeUpdate();
        }
    }
}

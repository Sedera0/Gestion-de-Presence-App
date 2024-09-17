package com.GestionPresence.Presence.repository;

import com.GestionPresence.Presence.entity.Cours;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursDAO {
    private Connection connection;

    public CoursDAO(Connection connection) {
        this.connection = connection;
    }

    public void addCours(Cours cours) throws SQLException {
        String query = "INSERT INTO cours (nom_cours) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cours.getNomCours());
            statement.executeUpdate();
        }
    }

    public Cours getCours(int coursId) throws SQLException {
        String query = "SELECT * FROM cours WHERE cours_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, coursId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Cours cours = new Cours();
                cours.setCoursId(resultSet.getInt("cours_id"));
                cours.setNomCours(resultSet.getString("nom_cours"));
                return cours;
            }
        }
        return null;
    }

    public List<Cours> getAllCours() throws SQLException {
        List<Cours> coursList = new ArrayList<>();
        String query = "SELECT * FROM cours";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Cours cours = new Cours();
                cours.setCoursId(resultSet.getInt("cours_id"));
                cours.setNomCours(resultSet.getString("nom_cours"));
                coursList.add(cours);
            }
        }
        return coursList;
    }

    public void updateCours(Cours cours) throws SQLException {
        String query = "UPDATE cours SET nom_cours = ? WHERE cours_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cours.getNomCours());
            statement.setInt(2, cours.getCoursId());
            statement.executeUpdate();
        }
    }

    public void deleteCours(int coursId) throws SQLException {
        String query = "DELETE FROM cours WHERE cours_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, coursId);
            statement.executeUpdate();
        }
    }
}

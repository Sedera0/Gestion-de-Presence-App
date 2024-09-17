package com.GestionPresence.Presence.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/presence";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "rafalimanana3$";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error when trying to connect to the database : " + e.getMessage());
            throw new RuntimeException("Connexion failed!", e);
        }
    }
}
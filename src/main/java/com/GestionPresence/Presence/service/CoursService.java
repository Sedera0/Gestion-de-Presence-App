package com.GestionPresence.Presence.service;

import com.GestionPresence.Presence.entity.Cours;
import com.GestionPresence.Presence.repository.CoursDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CoursService {
    private CoursDAO repository;

    public CoursService(Connection connection) {
        this.repository = new CoursDAO(connection);
    }

    public void addCours(Cours cours) throws SQLException {
        repository.addCours(cours);
    }

    public Cours getCours(int coursId) throws SQLException {
        return repository.getCours(coursId);
    }

    public List<Cours> getAllCours() throws SQLException {
        return repository.getAllCours();
    }

    public void updateCours(Cours cours) throws SQLException {
        repository.updateCours(cours);
    }

    public void deleteCours(int coursId) throws SQLException {
        repository.deleteCours(coursId);
    }
}

package com.GestionPresence.Presence.service;

import com.GestionPresence.Presence.entity.Etudiant;
import com.GestionPresence.Presence.repository.EtudiantDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EtudiantService {
    private EtudiantDAO repository;

    public EtudiantService(Connection connection) {
        this.repository = new EtudiantDAO(connection);
    }

    public void addEtudiant(Etudiant etudiant) throws SQLException {
        repository.addEtudiant(etudiant);
    }

    public Etudiant getEtudiant(String etudiantId) throws SQLException {
        return repository.getEtudiant(etudiantId);
    }

    public List<Etudiant> getAllEtudiants() throws SQLException {
        return repository.getAllEtudiants();
    }

    public void updateEtudiant(Etudiant etudiant) throws SQLException {
        repository.updateEtudiant(etudiant);
    }

    public void deleteEtudiant(String etudiantId) throws SQLException {
        repository.deleteEtudiant(etudiantId);
    }
}

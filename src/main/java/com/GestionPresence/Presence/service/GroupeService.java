package com.GestionPresence.Presence.service;

import com.GestionPresence.Presence.entity.Groupe;
import com.GestionPresence.Presence.repository.GroupeDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GroupeService {
    private GroupeDAO repository;

    public GroupeService(Connection connection) {
        this.repository = new GroupeDAO(connection);
    }

    public void addGroupe(Groupe groupe) throws SQLException {
        repository.addGroupe(groupe);
    }

    public Groupe getGroupe(int groupeId) throws SQLException {
        return repository.getGroupe(groupeId);
    }

    public List<Groupe> getAllGroupes() throws SQLException {
        return repository.getAllGroupes();
    }

    public void updateGroupe(Groupe groupe) throws SQLException {
        repository.updateGroupe(groupe);
    }

    public void deleteGroupe(int groupeId) throws SQLException {
        repository.deleteGroupe(groupeId);
    }
}

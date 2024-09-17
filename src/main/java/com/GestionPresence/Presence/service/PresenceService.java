package com.GestionPresence.Presence.service;

import com.GestionPresence.Presence.entity.Presence;
import com.GestionPresence.Presence.repository.PresenceDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PresenceService {
    private PresenceDAO repository;

    public PresenceService(Connection connection) {
        this.repository = new PresenceDAO(connection);
    }

    public void addPresence(Presence presence) throws SQLException {
        repository.addPresence(presence);
    }

    public Presence getPresence(int presenceId) throws SQLException {
        return repository.getPresence(presenceId);
    }

    public List<Presence> getAllPresences() throws SQLException {
        return repository.getAllPresences();
    }

    public void updatePresence(Presence presence) throws SQLException {
        repository.updatePresence(presence);
    }

    public void deletePresence(int presenceId) throws SQLException {
        repository.deletePresence(presenceId);
    }
}

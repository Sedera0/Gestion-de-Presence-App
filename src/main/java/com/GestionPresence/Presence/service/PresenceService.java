package com.GestionPresence.Presence.service;

import com.GestionPresence.Presence.config.DatabaseConnection;
import com.GestionPresence.Presence.entity.Presence;
import com.GestionPresence.Presence.entity.PresenceStatus;
import com.GestionPresence.Presence.repository.PresenceDAO;
import org.springframework.stereotype.Service;


import java.sql.SQLException;
import java.util.List;

@Service
public class PresenceService {

    private final PresenceDAO repository;

    public PresenceService(PresenceDAO repository) {
        this.repository = repository;
    }

    // Method to add a new presence
    public void addPresence(String studentId, PresenceStatus status) throws SQLException {
        Presence presence = new Presence();
        presence.setStudentId(studentId);
        presence.setStatus(status);
        repository.addPresence(presence);
    }

    public int countUnjustifiedAbsences(String studentId) throws SQLException {
        return repository.countUnjustifiedAbsences(studentId);
    }

    // Changed method visibility to public
    public List<Presence> getAllPresences(String studentId) throws SQLException {
        return repository.getAllPresencesByStudent(studentId); // Directly use studentId if int
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

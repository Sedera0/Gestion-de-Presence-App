package com.GestionPresence.Presence.service;

import com.GestionPresence.Presence.entity.Presence;
import com.GestionPresence.Presence.entity.PresenceStatus;
import com.GestionPresence.Presence.repository.PresenceDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PresenceService {
    private final PresenceDAO repository;

    public PresenceService(Connection connection) {
        this.repository = new PresenceDAO(connection);
    }

    // Méthode pour ajouter une nouvelle présence
    public void addPresence(int studentId, PresenceStatus status) throws SQLException {
        Presence presence = new Presence();
        presence.setStudentId(String.valueOf(studentId));
        presence.setStatus(status);

        // Sauvegarder la présence dans la base de données
        repository.addPresence(presence);
    }

    // Méthode pour compter les absences non justifiées
    public int countUnjustifiedAbsences(int studentId) throws SQLException {
        List<Presence> presences = getAllPresencesByStudent(studentId);

        // Compter les absences non justifiées
        long unjustifiedAbsences = presences.stream()
                .filter(presence -> presence.getStatus() == PresenceStatus.ABSENT)
                .count();

        return (int) unjustifiedAbsences;
    }

    private List<Presence> getAllPresencesByStudent(int studentId) throws SQLException {
        return repository.getAllPresencesByStudent(studentId);
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

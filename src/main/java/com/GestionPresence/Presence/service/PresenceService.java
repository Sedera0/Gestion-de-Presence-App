package com.GestionPresence.Presence.service;

import com.GestionPresence.Presence.entity.Presence;
import com.GestionPresence.Presence.entity.PresenceStatus;
import com.GestionPresence.Presence.repository.PresenceDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
public class PresenceService {

    private final PresenceDAO repository;

    public PresenceService(PresenceDAO repository) {
        this.repository = repository;
    }

    // Method to add a new presence
    public void addPresence(String studentId, Integer courseId, Date presenceDate, PresenceStatus status) throws SQLException {
        Presence presence = new Presence();
        presence.setStudentId(studentId);
        presence.setCourseId(courseId);
        presence.setPresenceDate(presenceDate);
        presence.setStatus(status);
        repository.addPresence(presence);
    }

    // Method to count unjustified absences
    public int countUnjustifiedAbsences(String studentId) throws SQLException {
        return repository.countUnjustifiedAbsences(studentId);
    }

    // Method to get a presence by its ID
    public Presence getPresence(int presenceId) throws SQLException {
        return repository.getPresence(presenceId);
    }

    // Method to get all presences
    public List<Presence> getAllPresences() throws SQLException {
        return repository.getAllPresences();
    }

    // Method to update a presence
    public void updatePresence(Presence presence) throws SQLException {
        repository.updatePresence(presence);
    }

    // Method to delete a presence
    public boolean deletePresence(int presenceId) throws SQLException {
        return repository.deletePresence(presenceId);
    }

    // Optionally implement an alternate updatePresence method if needed
    public boolean updatePresence(Integer id, String studentId, Integer courseId, Date presenceDate, PresenceStatus status) throws SQLException {
        Presence presence = new Presence();
        presence.setPresenceId(id);
        presence.setStudentId(studentId);
        presence.setCourseId(courseId);
        presence.setPresenceDate(presenceDate);
        presence.setStatus(status);
        return repository.updatePresence(presence);
    }



}

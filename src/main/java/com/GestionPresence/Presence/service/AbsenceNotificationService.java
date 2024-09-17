package com.GestionPresence.Presence.service;

import com.GestionPresence.Presence.entity.Student;
import com.GestionPresence.Presence.repository.StudentDAO;
import com.GestionPresence.Presence.repository.PresenceDAO; // Adjust as per your implementation
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class AbsenceNotificationService {
    private static final int ABSENCE_THRESHOLD = 3;
    private final PresenceDAO presenceDAO;
    private final StudentDAO studentDAO;

    @Autowired
    public AbsenceNotificationService(PresenceDAO presenceDAO, StudentDAO studentDAO) {
        this.presenceDAO = presenceDAO;
        this.studentDAO = studentDAO;
    }

    public void checkAndNotifyStudent(String studentId) throws SQLException {
        int unjustifiedAbsences = presenceDAO.countUnjustifiedAbsences(studentId);

        if (unjustifiedAbsences >= ABSENCE_THRESHOLD) {
            String message = "You have accumulated " + unjustifiedAbsences + " unjustified absences.";
            studentDAO.updateNotificationMessage(studentId, message);
        }
    }
}

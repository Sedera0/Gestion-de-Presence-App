package com.GestionPresence.Presence.service;

import com.GestionPresence.Presence.repository.PresenceDAO;
import com.GestionPresence.Presence.entity.AbsenceCountByCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AbsenceNotificationService {

    @Autowired
    private PresenceDAO presenceDAO;

    public List<AbsenceCountByCourse> getAbsencesByCourse(String studentId) throws SQLException {
        return presenceDAO.getAbsencesCountByCourse(studentId);
    }


    public String checkAbsencesAndNotifyStudent(String studentId) throws SQLException {
        List<AbsenceCountByCourse> absencesByCourse = presenceDAO.getAbsencesCountByCourse(studentId);
        int totalAbsences = absencesByCourse.stream().mapToInt(AbsenceCountByCourse::getAbsenceCount).sum();

        if (totalAbsences >= 3) {
            String message = "Vous avez un total de " + totalAbsences + " absences injustifiées. Veuillez régulariser votre situation.";
            presenceDAO.updateNotificationMessage(studentId, message);
            return message; // Retourne le message
        }

        return "Aucune notification nécessaire."; // Message par défaut
    }

}

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


    public void checkAbsencesAndNotifyStudent(String studentId) throws SQLException {
        List<AbsenceCountByCourse> absencesByCourse = presenceDAO.getAbsencesCountByCourse(studentId);

        // Calculer le total des absences
        int totalAbsences = absencesByCourse.stream().mapToInt(AbsenceCountByCourse::getAbsenceCount).sum();

        // Si le total est supérieur ou égal à 3, envoyer une notification
        if (totalAbsences >= 3) {
            String message = "Vous avez un total de " + totalAbsences + " absences injustifiées. Veuillez régulariser votre situation.";
            presenceDAO.updateNotificationMessage(studentId, message);
        }
    }
}

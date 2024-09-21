package com.GestionPresence.Presence.controller;

import com.GestionPresence.Presence.service.AbsenceNotificationService;
import com.GestionPresence.Presence.entity.AbsenceCountByCourse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/absences")
public class AbsenceNotificationController {

    private final AbsenceNotificationService absenceNotificationService;

    public AbsenceNotificationController(AbsenceNotificationService absenceNotificationService) {
        this.absenceNotificationService = absenceNotificationService;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<List<AbsenceCountByCourse>> getAbsencesByCourse(@PathVariable("studentId") String studentId) {
        try {
            List<AbsenceCountByCourse> absencesByCourse = absenceNotificationService.getAbsencesByCourse(studentId);
            return new ResponseEntity<>(absencesByCourse, HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/notify/{studentId}")
    public ResponseEntity<String> checkAndNotify(@PathVariable("studentId") String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return new ResponseEntity<>("Invalid student ID.", HttpStatus.BAD_REQUEST);
        }
        try {
            String notificationMessage = absenceNotificationService.checkAbsencesAndNotifyStudent(studentId);
            return new ResponseEntity<>(notificationMessage, HttpStatus.OK); // Renvoie le message de notification
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error occurred while checking absences: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

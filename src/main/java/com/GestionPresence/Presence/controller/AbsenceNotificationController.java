package com.GestionPresence.Presence.controller;

import com.GestionPresence.Presence.service.AbsenceNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/absences")
public class AbsenceNotificationController {

    private final AbsenceNotificationService absenceNotificationService;

    @Autowired
    public AbsenceNotificationController(AbsenceNotificationService absenceNotificationService) {
        this.absenceNotificationService = absenceNotificationService;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<String> checkAbsences(@PathVariable("studentId") String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return new ResponseEntity<>("Invalid student ID.", HttpStatus.BAD_REQUEST);
        }
        try {
            absenceNotificationService.checkAndNotifyStudent(studentId);
            return new ResponseEntity<>("Absence check complete.", HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error occurred while checking absences: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.GestionPresence.Presence.controller;

import com.GestionPresence.Presence.service.AbsenceNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/absences")
public class CorNotificationController {

    private final AbsenceNotificationService absenceNotificationService;

    @Autowired
    public CorNotificationController(AbsenceNotificationService absenceNotificationService) {
        this.absenceNotificationService = absenceNotificationService;
    }

    @GetMapping("/check/{studentId}")
    public ResponseEntity<String> checkAbsences(@PathVariable("studentId") String studentId) {
        try {
            absenceNotificationService.checkAndNotifyStudent(studentId);
            return new ResponseEntity<>("Absence check complete.", HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>("Error occurred while checking absences.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

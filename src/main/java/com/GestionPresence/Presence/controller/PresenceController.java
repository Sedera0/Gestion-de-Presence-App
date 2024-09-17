package com.GestionPresence.Presence.controller;

import com.GestionPresence.Presence.entity.PresenceStatus;
import com.GestionPresence.Presence.service.PresenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/presences")
public class PresenceController {

    private final PresenceService presenceService;

    public PresenceController(PresenceService presenceService) {
        this.presenceService = presenceService;
    }

    // Ajouter une présence
    @PostMapping
    public ResponseEntity<String> addPresence(@RequestParam String studentId, @RequestParam PresenceStatus status) {
        try {
            presenceService.addPresence(studentId, status);

            int unjustifiedAbsences = presenceService.countUnjustifiedAbsences(studentId);

            if (unjustifiedAbsences >= 3) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body("Absence ajoutée. Notification envoyée car les absences ont atteint " + unjustifiedAbsences);
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Présence ajoutée avec succès.");
        } catch (SQLException e) {
            // Log l'erreur et renvoie un message d'erreur approprié
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'ajout de la présence : " + e.getMessage());
        }
    }
}

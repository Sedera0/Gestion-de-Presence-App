package com.GestionPresence.Presence.controller;

import com.GestionPresence.Presence.entity.Presence;
import com.GestionPresence.Presence.entity.PresenceStatus;
import com.GestionPresence.Presence.service.PresenceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/presences")
public class PresenceController {

    private final PresenceService presenceService;

    public PresenceController(PresenceService presenceService) {
        this.presenceService = presenceService;
    }

    // Ajouter une présence
    @PostMapping
    public ResponseEntity<String> addPresence(
            @RequestParam String studentId,
            @RequestParam Integer courseId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date presenceDate,
            @RequestParam PresenceStatus status) {
        try {
            presenceService.addPresence(studentId, courseId, presenceDate, status);
            int unjustifiedAbsences = presenceService.countUnjustifiedAbsences(studentId);

            if (unjustifiedAbsences >= 3) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body("Absence ajoutée. Notification envoyée car les absences ont atteint " + unjustifiedAbsences);
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Présence ajoutée avec succès.");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'ajout de la présence : " + e.getMessage());
        }
    }


    // Lire toutes les présences
    @GetMapping
    public ResponseEntity<List<Presence>> getAllPresences() {
        try {
            List<Presence> presences = presenceService.getAllPresences();
            return ResponseEntity.status(HttpStatus.OK).body(presences);
        } catch (SQLException e) {
            // Log l'erreur et renvoie un message d'erreur approprié
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    // Lire une présence par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Presence> getPresenceById(
            @PathVariable("id") Integer id) {
        try {
            Presence presence = presenceService.getPresence(id);
            if (presence != null) {
                return ResponseEntity.status(HttpStatus.OK).body(presence);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    // Mettre à jour une présence
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePresence(
            @PathVariable("id") Integer id,
            @RequestParam String studentId,
            @RequestParam Integer courseId,
            @RequestParam Date presenceDate,
            @RequestParam PresenceStatus status)
            throws SQLException {
        boolean updated = presenceService.updatePresence(id, studentId, courseId, presenceDate, status);
        if (updated) {
            return ResponseEntity.status(HttpStatus.OK).body("Présence mise à jour avec succès.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Présence non trouvée.");
        }
    }

    // Supprimer une présence
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePresence(@PathVariable("id") Integer id) {
        try {
            boolean deleted = presenceService.deletePresence(id);
            if (deleted) {
                return ResponseEntity.status(HttpStatus.OK).body("Présence supprimée avec succès.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Présence non trouvée.");
            }
        } catch (SQLException e) {
            // Log l'erreur et renvoie un message d'erreur approprié
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression de la présence : " + e.getMessage());
        }
    }
}

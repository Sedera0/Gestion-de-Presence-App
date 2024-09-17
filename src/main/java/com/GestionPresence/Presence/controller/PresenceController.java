package com.GestionPresence.Presence.controller;

import com.GestionPresence.Presence.entity.PresenceStatus;
import com.GestionPresence.Presence.service.PresenceService;
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
    public String addPresence(@RequestParam int studentId, @RequestParam PresenceStatus status) throws SQLException {
        presenceService.addPresence(studentId, status);

        int unjustifiedAbsences = presenceService.countUnjustifiedAbsences(studentId);

        if (unjustifiedAbsences >= 3) {
            return "Absence ajoutée. Notification envoyée car les absences ont atteint " + unjustifiedAbsences;
        }

        return "Présence ajoutée avec succès.";
    }
}

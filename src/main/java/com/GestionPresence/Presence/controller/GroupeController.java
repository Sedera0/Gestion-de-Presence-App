package com.GestionPresence.Presence.controller;

import com.GestionPresence.Presence.entity.Groupe;
import com.GestionPresence.Presence.service.GroupeService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/groupes")
public class GroupeController {

    private final GroupeService groupeService;

    public GroupeController(GroupeService groupeService) {
        this.groupeService = groupeService;
    }

    @PostMapping
    public String addGroupe(@RequestBody Groupe groupe) throws SQLException {
        groupeService.addGroupe(groupe);
        return "Groupe ajouté avec succès.";
    }

    @GetMapping("/{groupeId}")
    public Groupe getGroupe(@PathVariable int groupeId) throws SQLException {
        return groupeService.getGroupe(groupeId);
    }

    @GetMapping
    public List<Groupe> getAllGroupes() throws SQLException {
        return groupeService.getAllGroupes();
    }

    @PutMapping("/{groupeId}")
    public String updateGroupe(@PathVariable int groupeId, @RequestBody Groupe groupe) throws SQLException {
        groupe.setGroupeId(groupeId);
        groupeService.updateGroupe(groupe);
        return "Groupe mis à jour avec succès.";
    }

    @DeleteMapping("/{groupeId}")
    public String deleteGroupe(@PathVariable int groupeId) throws SQLException {
        groupeService.deleteGroupe(groupeId);
        return "Groupe supprimé avec succès.";
    }
}

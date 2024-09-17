package com.GestionPresence.Presence.controller;

import com.GestionPresence.Presence.entity.Etudiant;
import com.GestionPresence.Presence.service.EtudiantService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class EtudiantController {

    private final EtudiantService etudiantServiceService;

    public EtudiantController(EtudiantService etudiantServiceService) {
        this.studentService = studentService;
    }

    @PostMapping
    public String addStudent(@RequestBody Etudiant etudiant) throws SQLException {
        studentService.addEtudiant(etudiant);
        return "Étudiant ajouté avec succès.";
    }

    @GetMapping("/{studentId}")
    public Etudiant getStudent(@PathVariable String studentId) throws SQLException {
        return studentService.getEtudiant(studentId);
    }

    @GetMapping
    public List<Etudiant> getAllStudents() throws SQLException {
        return studentService.getAllEtudiants();
    }

    @PutMapping("/{etudiantId}")
    public String updateStudent(@PathVariable String studentId, @RequestBody Etudiant etudiant) throws SQLException {
        etudiant.setEtudiantId(studentId);
        studentService.updateEtudiant(etudiant);
        return "Étudiant mis à jour avec succès.";
    }

    @DeleteMapping("/{etudiantId}")
    public String deleteStudent(@PathVariable String etudiantId) throws SQLException {
        studentService.deleteEtudiant(etudiantId);
        return "Étudiant supprimé avec succès.";
    }
}

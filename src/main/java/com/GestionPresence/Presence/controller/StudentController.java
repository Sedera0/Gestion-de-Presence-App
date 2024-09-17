package com.GestionPresence.Presence.controller;

import com.GestionPresence.Presence.entity.Student;
import com.GestionPresence.Presence.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public String addStudent(@RequestBody Student student) throws SQLException {
        studentService.addStudent(student);
        return "Student added successfully!";
    }

    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable String studentId) throws SQLException {
        return studentService.getStudent(studentId);
    }

    @GetMapping
    public List<Student> getAllStudents() throws SQLException {
        return studentService.getAllStudents();
    }

    @PutMapping("/{studentId}")
    public String updateStudent(@PathVariable String studentId, @RequestBody Student student) throws SQLException {
        student.setStudentId(studentId);
        studentService.updateStudent(student);
        return "Student updated successfully!";
    }

    @DeleteMapping("/{studentId}")
    public String deleteStudent(@PathVariable String studentId) throws SQLException {
        studentService.deleteStudent(studentId);
        return "Student deleted successfully!";
    }
}

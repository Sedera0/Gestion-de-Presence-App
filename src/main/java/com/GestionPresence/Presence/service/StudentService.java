package com.GestionPresence.Presence.service;

import com.GestionPresence.Presence.entity.Student;
import com.GestionPresence.Presence.repository.StudentDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class StudentService {
    private final StudentDAO repository;

    public StudentService(StudentDAO repository) {
        this.repository = repository;
    }

    public void addStudent(Student student) throws SQLException {
        validateStudent(student);
        repository.addStudent(student);
    }

    public Student getStudent(String studentId) throws SQLException {
        return repository.getStudent(studentId);
    }

    public List<Student> getAllStudents() throws SQLException {
        return repository.getAllStudents();
    }

    public void updateStudent(Student student) throws SQLException {
        validateStudent(student);
        repository.updateStudent(student);
    }

    public void deleteStudent(String studentId) throws SQLException {
        repository.deleteStudent(studentId);
    }

    private void validateStudent(Student student) {
        if (student == null || student.getStudentId() == null || student.getStudentId().isEmpty()) {
            throw new IllegalArgumentException("Student or Student ID cannot be null or empty");
        }
    }
}

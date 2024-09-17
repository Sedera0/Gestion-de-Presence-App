package com.GestionPresence.Presence.service;

import com.GestionPresence.Presence.entity.Student;
import com.GestionPresence.Presence.repository.StudentDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private StudentDAO repository;

    public StudentService(Connection connection) {
        this.repository = new StudentDAO(connection);
    }

    public void addStudent(Student student) throws SQLException {
        repository.addStudent(student);
    }

    public Student getStudent(String studentId) throws SQLException {
        return repository.getStudent(studentId);
    }

    public List<Student> getAllStudents() throws SQLException {
        return repository.getAllStudents();
    }

    public void updateStudent(Student student) throws SQLException {
        repository.updateStudent(student);
    }

    public void deleteStudent(String studentId) throws SQLException {
        repository.deleteStudent(studentId);
    }
}

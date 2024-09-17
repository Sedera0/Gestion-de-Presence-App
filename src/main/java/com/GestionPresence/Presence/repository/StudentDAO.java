package com.GestionPresence.Presence.repository;

import com.GestionPresence.Presence.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private final Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    public void addStudent(Student student) throws SQLException {
        String query = "INSERT INTO student (student_id, first_name, last_name, email, phone, birthday) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, student.getStudentId());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setString(4, student.getEmail());
            statement.setString(5, student.getPhone());
            statement.setDate(6, new java.sql.Date(student.getBirthday().getTime()));
            statement.executeUpdate();
        }
    }

    public Student getStudent(String studentId) throws SQLException {
        String query = "SELECT * FROM student WHERE student_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getString("student_id"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setLastName(resultSet.getString("last_name"));
                student.setEmail(resultSet.getString("email"));
                student.setPhone(resultSet.getString("phone"));
                student.setBirthday(resultSet.getDate("birthday"));
                return student;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM student";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getString("student_id"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setLastName(resultSet.getString("last_name"));
                student.setEmail(resultSet.getString("email"));
                student.setPhone(resultSet.getString("phone"));
                student.setBirthday(resultSet.getDate("birthday"));
                students.add(student);
            }
        }
        return students;
    }

    public void updateStudent(Student student) throws SQLException {
        String query = "UPDATE student SET first_name = ?, last_name = ?, email = ?, phone = ?, birthday = ? WHERE student_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getPhone());
            statement.setDate(5, new java.sql.Date(student.getBirthday().getTime()));
            statement.setString(6, student.getStudentId());
            statement.executeUpdate();
        }
    }

    public void deleteStudent(String studentId) throws SQLException {
        String query = "DELETE FROM student WHERE student_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, studentId);
            statement.executeUpdate();
        }
    }

    // Method to update the notification message
    public void updateNotificationMessage(String studentId, String message) throws SQLException {
        String query = "UPDATE student SET notification_message = ? WHERE student_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, message);
            statement.setString(2, studentId);
            statement.executeUpdate();
        }
    }

    // Method to get the notification message
    public String getNotificationMessage(String studentId) throws SQLException {
        String query = "SELECT notification_message FROM student WHERE student_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("notification_message");
            }
        }
        return null;
    }
}

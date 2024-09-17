package com.GestionPresence.Presence.repository;

import com.GestionPresence.Presence.entity.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private Connection connection;

    public CourseDAO(Connection connection) {
        this.connection = connection;
    }

    public void addCourse(Course course) throws SQLException {
        String query = "INSERT INTO course (course_name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, course.getCourseName());
            statement.executeUpdate();
        }
    }

    public Course getCourse(int courseId) throws SQLException {
        String query = "SELECT * FROM course WHERE course_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, courseId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Course course = new Course();
                course.setCourseId(resultSet.getInt("course_id"));
                course.setCourseName(resultSet.getString("course_name"));
                return course;
            }
        }
        return null;
    }

    public List<Course> getAllCourses() throws SQLException {
        List<Course> coursList = new ArrayList<>();
        String query = "SELECT * FROM course";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Course course = new Course();
                course.setCourseId(resultSet.getInt("course_id"));
                course.setCourseName(resultSet.getString("course_name"));
                coursList.add(course);
            }
        }
        return coursList;
    }

    public void updateCourse(Course course) throws SQLException {
        String query = "UPDATE course SET course_name = ? WHERE course_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, course.getCourseName());
            statement.setInt(2, course.getCourseId());
            statement.executeUpdate();
        }
    }

    public void deleteCourse(int courseId) throws SQLException {
        String query = "DELETE FROM course WHERE course_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, courseId);
            statement.executeUpdate();
        }
    }
}

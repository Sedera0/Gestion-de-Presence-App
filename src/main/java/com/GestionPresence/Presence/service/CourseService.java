package com.GestionPresence.Presence.service;

import com.GestionPresence.Presence.entity.Course;
import com.GestionPresence.Presence.repository.CourseDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CourseService {
    private CourseDAO repository;

    public CourseService(Connection connection) {
        this.repository = new CourseDAO(connection);
    }

    public void addCourse(Course course) throws SQLException {
        repository.addCourse(course);
    }

    public Course getCourse(int courseId) throws SQLException {
        return repository.getCourse(courseId);
    }

    public List<Course> getAllCourses() throws SQLException {
        return repository.getAllCourses();
    }

    public void updateCourse(Course course) throws SQLException {
        repository.updateCourse(course);
    }

    public void deleteCourse(int courseId) throws SQLException {
        repository.deleteCourse(courseId);
    }
}

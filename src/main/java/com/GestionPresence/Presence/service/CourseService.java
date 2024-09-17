package com.GestionPresence.Presence.service;

import com.GestionPresence.Presence.entity.Course;
import com.GestionPresence.Presence.repository.CourseDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CourseService {
    private final CourseDAO repository;

    public CourseService(CourseDAO repository) {
        this.repository = repository;
    }

    public Course addCourse(Course course) throws SQLException {
        repository.addCourse(course);
        return course;
    }

    public Course getCourseById(int courseId) throws SQLException {
        return repository.getCourse(courseId);
    }

    public List<Course> getAllCourses() throws SQLException {
        return repository.getAllCourses();
    }

    public Course updateCourse(Course course) throws SQLException {
        repository.updateCourse(course);
        return course;
    }

    public boolean deleteCourse(int courseId) throws SQLException {
        return repository.deleteCourse(courseId);
    }
}

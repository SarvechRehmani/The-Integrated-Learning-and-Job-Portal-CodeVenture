package com.codeventure.services;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Course;
import com.codeventure.entities.course.Lecture;

import java.util.List;

public interface CourseService {

    public Course addCourse(Course course);

    public Course updateCourse(Course course);

    public void deleteCourse(long id);

    public Course getSingleCourse(long id);

    public List<Course> getAllCourses();

    public List<Course> getCoursesByUser(User user);

    public Course getSingleCourseUser(long id, User user);

    public List<Course> getCoursesByField(String field);

}

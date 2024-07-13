package com.codeventure.services.impl;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Course;
import com.codeventure.entities.course.Lecture;
import com.codeventure.entities.result.AssignmentResult;
import com.codeventure.entities.result.LabTaskResult;
import com.codeventure.entities.result.QuizResult;
import com.codeventure.helper.DeleteService;
import com.codeventure.repo.UserRepository;
import com.codeventure.repo.course.AssignmentRepository;
import com.codeventure.repo.course.CourseRepository;
import com.codeventure.repo.course.LabTaskRepository;
import com.codeventure.repo.course.QuizRepository;
import com.codeventure.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private DeleteService deleteService;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Course addCourse(Course course) {
        return this.courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        Course course1 = this.getSingleCourse(course.getcId());
        course.setLectures(course1.getLectures());
        return this.courseRepository.save(course);
    }
    @Override
    public void deleteCourse(long id) {
        this.deleteService.deleteCourse(id);
    }

    @Override
    public Course getSingleCourse(long id) {
        return this.courseRepository.findById(id).get();
    }

    @Override
    public List<Course> getAllCourses() {
        return this.courseRepository.findAll();
    }

    @Override
    public List<Course> getCoursesByUser(User user) {
        return this.courseRepository.findByUser(user);
    }

    @Override
    public Course getSingleCourseUser(long id, User user) {
        List<Course> courses = this.getCoursesByUser(user);
        for (Course course : courses) {
            if (course.getcId() == id) {
                return course;
            }
        }
        return null;
    }

    @Override
    public List<Course> getCoursesByField(String field) {
        return this.courseRepository.findByField(field);
    }
}

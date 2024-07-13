package com.codeventure.services.impl;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Assignment;
import com.codeventure.entities.course.Course;
import com.codeventure.entities.course.Lecture;
import com.codeventure.entities.result.AssignmentResult;
import com.codeventure.helper.DeleteService;
import com.codeventure.repo.course.AssignmentRepository;
import com.codeventure.repo.course.LabTaskRepository;
import com.codeventure.repo.course.LectureRepository;
import com.codeventure.repo.course.QuizRepository;
import com.codeventure.services.LabTaskService;
import com.codeventure.services.LectureService;
import com.codeventure.services.QuizResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LectureServiceImpl implements LectureService {

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private DeleteService deleteService;

    @Autowired
    private CourseServiceImpl courseService;

    @Override
    public Lecture addLecture(Lecture lecture) {
        return this.lectureRepository.save(lecture);
    }

    @Override
    public Lecture updateLecture(Lecture lecture) {
        Lecture lecture1 = this.getSingleLecture(lecture.getlId());
        lecture.setAssignment(lecture1.getAssignment());
        lecture.setQuiz(lecture1.getQuiz());
        lecture.setLabTask(lecture1.getLabTask());
        return this.lectureRepository.save(lecture);
    }

    @Override
    public void deleteLecture(long id) {
        this.deleteService.deleteLecture(id);
    }

    @Override
    public Lecture getSingleLecture(long id) {
        return this.lectureRepository.findById(id).get();
    }

    @Override
    public List<Lecture> getAllLectures() {
        return this.lectureRepository.findAll();
    }

    @Override
    public List<Lecture> getUserCoursesLecture(User user) {
        List<Course> courses = this.courseService.getCoursesByUser(user);
        List<Lecture> lectures = new ArrayList<>();
        for (Course course : courses) {
            lectures.addAll(course.getLectures());
        }
        return lectures;
    }

    @Override
    public List<Lecture> getLectureByCourse(long cId) {
        Course course = this.courseService.getSingleCourse(cId);
        return this.lectureRepository.findByCourse(course);
    }

    @Override
    public List<Lecture> getLectureByCourseOfUser(long cId, User user) {
        Course course = this.courseService.getSingleCourseUser(cId, user);
        if (course != null) {
            return course.getLectures();
        } else {
            return null;
        }
    }

    @Override
    public Lecture getSingleLectureUser(long id, User user) {
        List<Lecture> lectures = this.getUserCoursesLecture(user);
        for (Lecture lecture : lectures) {
            if (lecture.getlId() == id) {
                return lecture;
            }
        }
        return null;
    }

    @Override
    public Lecture getLectureByCourseAndLNo(Course course, long lNo) {
        return this.lectureRepository.findByCourseAndLno(course, lNo);
    }

    @Override
    public int countLecturesOfCourse(long cId) {
        Course course = this.courseService.getSingleCourse(cId);
        return course.getLectures().size();
    }

    @Override
    public List<Lecture> getLecturesByField(String field) {
        List<Course> courses = this.courseService.getCoursesByField(field);
        List<Lecture> lectureList = new ArrayList<>();
        for (Course course: courses){
            if(course.getLectures() != null){
                lectureList.addAll(course.getLectures());
            }
        }
        return lectureList;
    }
}

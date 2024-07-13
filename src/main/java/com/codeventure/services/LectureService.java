package com.codeventure.services;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Course;
import com.codeventure.entities.course.Lecture;

import java.util.List;

public interface LectureService {

    public Lecture addLecture(Lecture lecture);

    public Lecture updateLecture(Lecture lecture);

    public void deleteLecture(long id);

    public Lecture getSingleLecture(long id);

    public List<Lecture> getAllLectures();

    public List<Lecture> getUserCoursesLecture(User user);

    public List<Lecture> getLectureByCourse(long id);

    public List<Lecture> getLectureByCourseOfUser(long cId, User user);

    public Lecture getSingleLectureUser(long id, User user);

    public Lecture getLectureByCourseAndLNo(Course course, long lNo);

    public int countLecturesOfCourse(long cId);

    public List<Lecture> getLecturesByField(String field);
}




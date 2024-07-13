package com.codeventure.controller;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Course;
import com.codeventure.entities.course.Lecture;
import com.codeventure.services.impl.LectureServiceImpl;
import com.codeventure.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lecture")
@CrossOrigin("*")
public class LectureController {

    @Autowired
    private LectureServiceImpl lectureService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/")
    public Lecture addLecture(@RequestBody Lecture lecture) {
        return this.lectureService.addLecture(lecture);
    }

    @PutMapping("/")
    public Lecture updateLecture(@RequestBody Lecture lecture) {
        return this.lectureService.updateLecture(lecture);
    }

    @DeleteMapping("/{id}")
    public void deleteLecture(@PathVariable("id") long id) {
        this.lectureService.deleteLecture(id);
    }

    @GetMapping("/{id}")
    public Lecture getSingleLecture(@PathVariable("id") long id) {
        return this.lectureService.getSingleLecture(id);
    }

    @GetMapping("/")
    public List<Lecture> getAllLectures() {
        return this.lectureService.getAllLectures();
    }

    @GetMapping("/user")
    public List<Lecture> getUserCoursesLectures(Principal principal) {
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        return this.lectureService.getUserCoursesLecture(user);
    }

    @GetMapping("/course/{cId}")
    public List<Lecture> getLecturesByCourse(@PathVariable("cId") long cId) {
        return this.lectureService.getLectureByCourse(cId);
    }

    @GetMapping("/course/without/assignment/{cId}")
    public List<Lecture> getLecturesByCourseWithoutAssignment(@PathVariable("cId") long cId) {
        List<Lecture> lectures = this.lectureService.getLectureByCourse(cId);
        List<Lecture> lectureList = new ArrayList<>();
        for (Lecture lecture : lectures) {
            if (lecture.getAssignment() == null) {
                lectureList.add(lecture);
            }
        }
        return lectureList;
    }
    @GetMapping("/course/without/labtask/{cId}")
    public List<Lecture> getLecturesByCourseWithoutLabTask(@PathVariable("cId") long cId) {
        List<Lecture> lectures = this.lectureService.getLectureByCourse(cId);
        List<Lecture> lectureList = new ArrayList<>();
        for (Lecture lecture : lectures) {
            if (lecture.getLabTask() == null) {
                lectureList.add(lecture);
            }
        }
        return lectureList;
    }

    @GetMapping("/course/without/quiz/{cId}")
    public List<Lecture> getLecturesByCourseWithoutQuiz(@PathVariable("cId") long cId) {
        List<Lecture> lectures = this.lectureService.getLectureByCourse(cId);
        List<Lecture> lectureList = new ArrayList<>();
        for (Lecture lecture : lectures) {
            if (lecture.getQuiz() == null) {
                lectureList.add(lecture);
            }
        }
        return lectureList;
    }

    @GetMapping("/course/user/{cId}")
    public List<Lecture> getLecturesByCourseOfUser(@PathVariable("cId") long cId, Principal principal) {
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        return this.lectureService.getLectureByCourseOfUser(cId, user);
    }

    @GetMapping("/user/{id}")
    public Lecture getSingleLectureUser(@PathVariable("id") long id, Principal principal) {
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        return this.lectureService.getSingleLectureUser(id, user);
    }

    @GetMapping("/user/lectureNo/{cId}/{lNo}")
    public Lecture getLectureByCourseAndLNo(@PathVariable("cId") long cId, @PathVariable("lNo") long lNo) {
        Course course = new Course();
        course.setcId(cId);
        return this.lectureService.getLectureByCourseAndLNo(course, lNo);
    }

    @GetMapping("/count/lecture/course/{cId}")
    public int getSingleLectureUser(@PathVariable("cId") long cId) {
        return this.lectureService.countLecturesOfCourse(cId);
    }

    @GetMapping("/field/{field}")
    public List<Lecture> getLecturesByField(@PathVariable("field") String field) {
        return this.lectureService.getLecturesByField(field);
    }
}

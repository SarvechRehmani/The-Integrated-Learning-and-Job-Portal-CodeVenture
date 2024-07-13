package com.codeventure.services.impl;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Assignment;
import com.codeventure.entities.course.Course;
import com.codeventure.entities.course.Lecture;
import com.codeventure.entities.result.AssignmentResult;
import com.codeventure.helper.DeleteService;
import com.codeventure.repo.UserRepository;
import com.codeventure.repo.course.AssignmentRepository;
import com.codeventure.services.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private DeleteService deleteService;

    @Autowired
    private LectureServiceImpl lectureService;

    @Override
    public Assignment addAssignment(Assignment assignment) {
        return this.assignmentRepository.save(assignment);
    }

    @Override
    public Assignment updateAssignment(Assignment assignment) {
        Assignment assignment1 = this.getSingleAssignment(assignment.getaId());
        assignment.setAssignmentResults(assignment1.getAssignmentResults());
        return this.assignmentRepository.save(assignment);
    }

    @Override
    public void deleteAssignment(long id) {
        this.deleteService.deleteAssignment(id);
    }

    @Override
    public Assignment getSingleAssignment(long id) {
        return this.assignmentRepository.findById(id).get();
    }

    @Override
    public List<Assignment> getAllAssignments() {
        return this.assignmentRepository.findAll();
    }

    @Override
    public Assignment getAssignmentByLecture(long lId) {
        Lecture lecture = this.lectureService.getSingleLecture(lId);
        return this.assignmentRepository.findByLecture(lecture);
    }


    @Override
    public List<Assignment> getAssignmentsByUserCourses(User user) {
        List<Lecture> lectures = this.lectureService.getUserCoursesLecture(user);
        List<Assignment> assignments = new ArrayList<>();
        for (Lecture lecture : lectures) {
            if (lecture.getAssignment() != null) {
                assignments.add(lecture.getAssignment());
            }
        }
        return assignments;
    }

    @Override
    public List<Assignment> getAssignmentsByField(String field) {
        List<Lecture> lectures = this.lectureService.getLecturesByField(field);
        List<Assignment> assignments = new ArrayList<>();
        for (Lecture lecture: lectures){
           if(lecture.getAssignment() != null){
               assignments.add(lecture.getAssignment());
           }
        }
        return assignments;
    }
}

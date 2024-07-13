package com.codeventure.services;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Assignment;

import java.util.List;

public interface AssignmentService {

    public Assignment addAssignment(Assignment assignment);

    public Assignment updateAssignment(Assignment assignment);

    public void deleteAssignment(long id);

    public Assignment getSingleAssignment(long id);

    public List<Assignment> getAllAssignments();

    public Assignment getAssignmentByLecture(long lId);

    public List<Assignment> getAssignmentsByUserCourses(User user);
    public List<Assignment> getAssignmentsByField(String field);
}

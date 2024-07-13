package com.codeventure.services;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Assignment;
import com.codeventure.entities.result.AssignmentResult;

import java.util.List;

public interface AssignmentResultService {
    public AssignmentResult saveAssignmentResult(AssignmentResult assignmentResult);
    public void deleteAssignmentResult(long arId);

    public AssignmentResult getAssignmentResultByAssignmentAndUser(Assignment assignment, User user);

    public AssignmentResult assignMarksAssignmentResult(AssignmentResult assignmentResult);

    public List<AssignmentResult> getAllAssignmentResultByAssignment(Assignment assignment);

    public List<AssignmentResult> getAssignmentResultsByUser(User user);
}

package com.codeventure.services.impl;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Assignment;
import com.codeventure.entities.result.AssignmentResult;
import com.codeventure.helper.DeleteService;
import com.codeventure.repo.UserRepository;
import com.codeventure.repo.course.AssignmentRepository;
import com.codeventure.repo.result.AssignmentResultRepository;
import com.codeventure.services.AssignmentResultService;
import com.codeventure.services.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentResultServiceImpl implements AssignmentResultService {
    @Autowired
    private AssignmentResultRepository assignmentResultRepository;

    @Autowired
    private DeleteService deleteService;

    @Override
    public AssignmentResult saveAssignmentResult(AssignmentResult assignmentResult) {
        return this.assignmentResultRepository.save(assignmentResult);
    }
    @Override
    public void deleteAssignmentResult(long arId){
        this.deleteService.deleteAssignmentResult(arId);
    }
    @Override
    public AssignmentResult getAssignmentResultByAssignmentAndUser(Assignment assignment, User user) {
        return this.assignmentResultRepository.findByAssignmentAndUser(assignment,user);
    }

    @Override
    public AssignmentResult assignMarksAssignmentResult(AssignmentResult assignmentResult) {
        return this.assignmentResultRepository.save(assignmentResult);
    }

    @Override
    public List<AssignmentResult> getAllAssignmentResultByAssignment(Assignment assignment) {
        return this.assignmentResultRepository.findByAssignment(assignment);
    }

    @Override
    public List<AssignmentResult> getAssignmentResultsByUser(User user) {
        return this.assignmentResultRepository.findByUser(user);
    }

    public AssignmentResult getSingleAssignmentResultById(long id) {
        return this.assignmentResultRepository.findById(id).get();
    }
}

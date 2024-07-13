package com.codeventure.repo.result;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Assignment;
import com.codeventure.entities.result.AssignmentResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentResultRepository extends JpaRepository<AssignmentResult, Long> {

    public AssignmentResult findByAssignmentAndUser(Assignment assignment, User user);
    public List<AssignmentResult> findByAssignment(Assignment assignment);

    public List<AssignmentResult> findByUser(User user);
}

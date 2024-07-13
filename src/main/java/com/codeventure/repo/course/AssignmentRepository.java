package com.codeventure.repo.course;

import com.codeventure.entities.course.Assignment;
import com.codeventure.entities.course.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    public Assignment findByLecture(Lecture lecture);
}

package com.codeventure.repo.course;

import com.codeventure.entities.course.LabTask;
import com.codeventure.entities.course.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabTaskRepository extends JpaRepository<LabTask, Long> {
    public LabTask findByLecture(Lecture lecture);
}

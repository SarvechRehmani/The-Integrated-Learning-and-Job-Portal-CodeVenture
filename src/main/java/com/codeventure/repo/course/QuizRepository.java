package com.codeventure.repo.course;

import com.codeventure.entities.course.Lecture;
import com.codeventure.entities.course.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    public Quiz findByLecture(Lecture lecture);
}

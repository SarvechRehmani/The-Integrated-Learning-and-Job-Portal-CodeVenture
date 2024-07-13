package com.codeventure.repo.course;

import com.codeventure.entities.course.Question;
import com.codeventure.entities.course.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    public List<Question> findByQuiz(Quiz quiz);

}

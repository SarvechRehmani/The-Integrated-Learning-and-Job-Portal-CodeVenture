package com.codeventure.repo.result;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Quiz;
import com.codeventure.entities.result.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {

    public QuizResult findByQuizAndUser(Quiz quiz, User user);

    public List<QuizResult> findByUser(User user);
}

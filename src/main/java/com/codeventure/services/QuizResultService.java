package com.codeventure.services;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Quiz;
import com.codeventure.entities.result.QuizResult;

import java.util.List;

public interface QuizResultService {

    public QuizResult saveResult(QuizResult quizResult);

    public void deleteQuizResult(long qId);

    public QuizResult getSingleQuizResultById(long qId);

    public QuizResult getQuizResult(Quiz quiz, User user);

    public List<QuizResult> getQuizResultByUser(User user);


}

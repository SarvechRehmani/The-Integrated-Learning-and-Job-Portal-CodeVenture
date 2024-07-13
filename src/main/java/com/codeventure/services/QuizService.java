package com.codeventure.services;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Assignment;
import com.codeventure.entities.course.Quiz;

import java.util.List;

public interface QuizService {

    public Quiz addQuiz(Quiz quiz);

    public Quiz updateQuiz(Quiz quiz);

    public void deleteQuiz(long id);


    public Quiz getSingleQuiz(long id);

    public List<Quiz> getAllQuizzes();

    public Quiz getQuizByLecture(long lId);

    public List<Quiz> getQuizByUserCourses(User user);

    public List<Quiz> getQuizByField(String field);
}

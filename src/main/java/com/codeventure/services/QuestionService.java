package com.codeventure.services;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Question;

import java.util.List;

public interface QuestionService {

    public Question addQuestion(Question question, User user) throws Exception;

    public Question updateQuestion(Question question);

    public void deleteQuestion(long id);

    public Question getSingleQuestion(long id);

    public List<Question> getAllQuestions();

    public List<Question> getQuestionsByQuizOfUser(long qId, User user);

    public Question getSingleQuestionUser(long id, User user);

    public List<Question> getQuestionByQuiz(long qId);

    public List<Question> getQuestionByQuizWithAnswers(long qId);

    public long countQuestionOfQuiz(long qId);
}

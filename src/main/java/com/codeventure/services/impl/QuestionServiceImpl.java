package com.codeventure.services.impl;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Question;
import com.codeventure.entities.course.Quiz;
import com.codeventure.helper.QuizNotFoundException;
import com.codeventure.helper.UserFoundException;
import com.codeventure.repo.course.QuestionRepository;
import com.codeventure.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizServiceImpl quizService;
    @Override
    public Question addQuestion(Question question, User user) throws QuizNotFoundException {
        boolean flag = false;
        List<Quiz> quizzes = this.quizService.getQuizByUserCourses(user);
        for (Quiz quiz: quizzes){
            if(quiz.getqId() == question.getQuiz().getqId()){
                flag = true;
                break;
            }
        }
        if (flag){
            return this.questionRepository.save(question);
        }else {
            System.out.println("Quiz not found");
            throw new QuizNotFoundException("Quiz Not Found..");
        }
    }

    @Override
    public Question updateQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(long id) {
        Question question =  this.getSingleQuestion(id);
        Quiz quiz = question.getQuiz();
        quiz.getQuestions().remove(question);
        this.quizService.updateQuiz(quiz);
    }

    @Override
    public Question getSingleQuestion(long id) {
        return this.questionRepository.findById(id).get();
    }

    @Override
    public List<Question> getAllQuestions() {
        return this.questionRepository.findAll();
    }

//    Get List Of Question Of User's Quiz
    @Override
    public List<Question> getQuestionsByQuizOfUser(long qId, User user) {
        List<Quiz> quizzes = this.quizService.getQuizByUserCourses(user);
        List<Question> questions = new ArrayList<>();
        for (Quiz quiz: quizzes){
            if (quiz.getqId() == qId){
                questions.addAll(quiz.getQuestions());
                break;
            }
        }
        return questions;
    }


//    Get Single Question Of User
    @Override
    public Question getSingleQuestionUser(long id, User user) {
        List<Quiz> quizzes = this.quizService.getQuizByUserCourses(user);
        Question question = null;
        for (Quiz quiz: quizzes){
            for (Question question1: quiz.getQuestions()){
                if(question1.getQuestionId() == id){
                    question = question1;
                }
            }
        }
        return question;
    }

//    Get Questions By Quiz
    @Override
    public List<Question> getQuestionByQuiz(long qId){
        Quiz quiz = this.quizService.getSingleQuiz(qId);
        List<Question> questions = quiz.getQuestions();
        Collections.shuffle(questions);
        if(questions.size() > quiz.getNumberOfQuestions()){
            questions = questions.subList(0,(int)quiz.getNumberOfQuestions());
        }
        questions.forEach(question -> {
            question.setAnswer("");
        });
        return questions;
    }

    @Override
    public List<Question> getQuestionByQuizWithAnswers(long qId){
        Quiz quiz = this.quizService.getSingleQuiz(qId);
        return quiz.getQuestions();
    }

    @Override
    public long countQuestionOfQuiz(long qId) {
        Quiz quiz = this.quizService.getSingleQuiz(qId);
        return quiz.getQuestions().size();
    }
}

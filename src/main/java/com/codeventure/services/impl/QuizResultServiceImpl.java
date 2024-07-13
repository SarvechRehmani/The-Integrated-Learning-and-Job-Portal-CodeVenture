package com.codeventure.services.impl;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Quiz;
import com.codeventure.entities.result.LabTaskResult;
import com.codeventure.entities.result.QuizResult;
import com.codeventure.helper.DeleteService;
import com.codeventure.repo.UserRepository;
import com.codeventure.repo.course.QuizRepository;
import com.codeventure.repo.result.QuizResultRepository;
import com.codeventure.services.QuizResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizResultServiceImpl implements QuizResultService {

    @Autowired
    private QuizResultRepository quizResultRepository;

    @Autowired
    private DeleteService deleteService;
    @Override
    public QuizResult saveResult(QuizResult quizResult) {
        return this.quizResultRepository.save(quizResult);
    }

    @Override
    public void deleteQuizResult(long qId) {
        this.deleteService.deleteQuizResult(qId);
    }

    @Override
    public QuizResult getSingleQuizResultById(long qId) {
        return this.quizResultRepository.findById(qId).get();
    }

    @Override
    public QuizResult getQuizResult(Quiz quiz, User user) {
        return this.quizResultRepository.findByQuizAndUser(quiz,user);
    }


    @Override
    public List<QuizResult> getQuizResultByUser(User user) {
        return this.quizResultRepository.findByUser(user);
    }
}

package com.codeventure.services.impl;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Assignment;
import com.codeventure.entities.course.Course;
import com.codeventure.entities.course.Lecture;
import com.codeventure.entities.course.Quiz;
import com.codeventure.entities.result.QuizResult;
import com.codeventure.helper.DeleteService;
import com.codeventure.repo.UserRepository;
import com.codeventure.repo.course.QuizRepository;
import com.codeventure.services.LectureService;
import com.codeventure.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private DeleteService deleteService;

    @Autowired
    private LectureService lectureService;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        Quiz quiz1 = this.getSingleQuiz(quiz.getqId());
        quiz.setQuestions(quiz1.getQuestions());
        quiz.setQuizResult(quiz1.getQuizResult());
        return this.quizRepository.save(quiz);
    }

    @Override
    public void deleteQuiz(long id) {
        this.deleteService.deleteQuiz(id);
    }

    @Override
    public Quiz getSingleQuiz(long id) {
        return this.quizRepository.findById(id).get();
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        return this.quizRepository.findAll();
    }

    @Override
    public Quiz getQuizByLecture(long lId) {
        Lecture lecture = this.lectureService.getSingleLecture(lId);
        return this.quizRepository.findByLecture(lecture);
    }

    @Override
    public List<Quiz> getQuizByUserCourses(User user) {
        List<Lecture> lectures = this.lectureService.getUserCoursesLecture(user);
        List<Quiz> quizzes = new ArrayList<>();
        for (Lecture lecture : lectures) {
            if (lecture.getQuiz() != null) {
                quizzes.add(lecture.getQuiz());
            }
        }
        return quizzes;
    }

    @Override
    public List<Quiz> getQuizByField(String field) {
        List<Lecture> lectures = this.lectureService.getLecturesByField(field);
        List<Quiz> quizzes = new ArrayList<>();
        for (Lecture lecture: lectures){
            if(lecture.getQuiz() != null){
                quizzes.add(lecture.getQuiz());
            }
        }
        return quizzes;
    }
}

package com.codeventure.controller;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Question;
import com.codeventure.entities.course.Quiz;
import com.codeventure.services.impl.QuestionServiceImpl;
import com.codeventure.services.impl.QuizServiceImpl;
import com.codeventure.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private QuizServiceImpl quizService;

    @Autowired
    private QuestionServiceImpl questionService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/")
    public Quiz addQuiz(@RequestBody Quiz quiz){
        return this.quizService.addQuiz(quiz);
    }

    @PutMapping("/")
    public Quiz updateQuiz(@RequestBody Quiz quiz){
        return this.quizService.updateQuiz(quiz);
    }

    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable("id") long id){
        this.quizService.deleteQuiz(id);
    }

    @GetMapping("/{id}")
    public Quiz getSingleQuiz(@PathVariable("id") long id){
        return this.quizService.getSingleQuiz(id);
    }

    @GetMapping("/")
    public List<Quiz> getAllQuizzes(){
        return this.quizService.getAllQuizzes();
    }

    @GetMapping("/user")
    public List<Quiz> getQuizzesByUserCourses(Principal principal){
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        return this.quizService.getQuizByUserCourses(user);
    }

    @GetMapping("/lecture/{lId}")
    public Quiz getQuizByLecture(@PathVariable("lId") long lId){
        return this.quizService.getQuizByLecture(lId);
    }

    @GetMapping("/field/{field}")
    public List<Quiz> getQuizByLecture(@PathVariable("field") String field){
        return this.quizService.getQuizByField(field);
    }
}

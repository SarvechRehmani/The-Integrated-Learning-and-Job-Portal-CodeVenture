package com.codeventure.controller;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Question;
import com.codeventure.helper.QuizNotFoundException;
import com.codeventure.repo.course.QuestionRepository;
import com.codeventure.services.impl.QuestionServiceImpl;
import com.codeventure.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionServiceImpl questionService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/")
    public Question addQuestion(@RequestBody Question question, Principal principal) throws Exception {
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        return this.questionService.addQuestion(question, user);
    }

    @PutMapping("/")
    public Question updateQuestion(@RequestBody Question question) {
        return this.questionService.updateQuestion(question);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable("id") long id) {
        this.questionService.deleteQuestion(id);
    }

    //    Get Question By id
    @GetMapping("/{id}")
    public Question getSingleQuestion(@PathVariable("id") long id) {
        return this.questionService.getSingleQuestion(id);
    }

    //  Get All Questions
    @GetMapping("/")
    public List<Question> getAllQuestions() {
        return this.questionService.getAllQuestions();
    }

    //    Get Questions By Users Quiz
    @GetMapping("/quiz/user/{qId}")
    public List<Question> getQuestionsByQuizOfUser(@PathVariable("qId") long qId, Principal principal) {
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        return this.questionService.getQuestionsByQuizOfUser(qId, user);
    }

    //    Get Questions by User
    @GetMapping("/user/{id}")
    public Question getSingleQuestionUser(@PathVariable("id") long id, Principal principal) {
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        return this.questionService.getSingleQuestionUser(id, user);
    }

    //    Get Questions by Quiz
    @GetMapping("/quiz/{qId}")
    public List<Question> getQuestionByQuiz(@PathVariable("qId") long qId) {
        return this.questionService.getQuestionByQuiz(qId);
    }

    //    Get Questions by Quiz
    @GetMapping("/quiz/answers/{qId}")
    public List<Question> getQuestionByQuizWithAnswers(@PathVariable("qId") long qId) {
        return this.questionService.getQuestionByQuizWithAnswers(qId);
    }


    //    Count Question Of Quiz
    @GetMapping("/count/{qId}")
    public long countQuestionOfQuiz(@PathVariable("qId") long qId) {
        return 10;
    }


    //    Handling Error for this class.
    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<?> exceptionHandler(QuizNotFoundException e) {
        return ResponseEntity.badRequest().body(e);
    }
}

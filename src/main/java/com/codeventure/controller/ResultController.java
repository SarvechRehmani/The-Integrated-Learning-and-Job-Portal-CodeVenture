package com.codeventure.controller;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Assignment;
import com.codeventure.entities.course.LabTask;
import com.codeventure.entities.course.Question;
import com.codeventure.entities.course.Quiz;
import com.codeventure.entities.result.AssignmentResult;
import com.codeventure.entities.result.LabTaskResult;
import com.codeventure.entities.result.QuizResult;
import com.codeventure.services.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/result")
@CrossOrigin("*")
public class ResultController {

    @Autowired
    private QuestionServiceImpl questionService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private QuizResultServiceImpl quizResultService;

    @Autowired
    private AssignmentResultServiceImpl assignmentResultService;

    @Autowired
    private LabTaskResultServiceImpl labTaskResultService;



//    QUIZ OPERATIONS   //
//    Submitting Quiz by User and Calculating Result...
    @PostMapping("/quiz")
    public ResponseEntity<?> quizResult(@RequestBody List<Question> questions, Principal principal) {
        int correctAnswer = 0;
        double gotMarks = 0;
        int attempted = 0;
        double singleQuestionMark = (float)questions.get(0).getQuiz().getMaxMarks() / questions.size();
        for (Question q : questions) {
            Question question = this.questionService.getSingleQuestion(q.getQuestionId());
            if (question.getAnswer().equalsIgnoreCase(q.getGivenAnswer())) {
                correctAnswer++;
                gotMarks += singleQuestionMark;
            }
            if (q.getGivenAnswer() != null || !q.getGivenAnswer().equals("")) {
                attempted++;
            }
        }
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        QuizResult quizResult = new QuizResult(questions.get(0).getQuiz(), user, attempted, gotMarks, correctAnswer,dateFormat.format(date));

        this.quizResultService.saveResult(quizResult);

        return ResponseEntity.ok(quizResult);
    }

//    Getting Quiz Result
    @GetMapping("/quiz/{qId}")
    public QuizResult getQuizResult(@PathVariable("qId") long qId, Principal principal) {
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        Quiz quiz = new Quiz();
        quiz.setqId(qId);
        return this.quizResultService.getQuizResult(quiz, user);
    }

    @DeleteMapping("/quiz/{qrId}")
    public void deleteQuizResult(@PathVariable("qrId") long qrId){
        this.quizResultService.deleteQuizResult(qrId);
    }




    //    ASSIGNMENT OPERATIONS   //
    //    User Submit Assignment
    @PostMapping("/assignment")
    public AssignmentResult submitAssignment(@RequestBody Assignment assignment, Principal principal) {
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        AssignmentResult assignmentResult = new AssignmentResult(assignment,user,assignment.getaAnswer());
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        assignmentResult.setDate(dateFormat.format(date));
        return this.assignmentResultService.saveAssignmentResult(assignmentResult);
    }

    //    Mentor Assigning Marks to the user Assignment
    @PutMapping("/assignment/marks")
    public AssignmentResult submitMarksAssignmentResult(@RequestBody AssignmentResult assignmentResult) {
        return this.assignmentResultService.assignMarksAssignmentResult(assignmentResult);
    }

    //    Getting Assignment Result...
    @GetMapping("/assignment/{aId}")
    public AssignmentResult getAssignmentResultByAssignmentAndUser(@PathVariable("aId") long aId, Principal principal) {
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        Assignment assignment = new Assignment();
        assignment.setaId(aId);
        return this.assignmentResultService.getAssignmentResultByAssignmentAndUser(assignment, user);
    }

//    Get AssignmentResult by AssignmentId and UserId
    @GetMapping("/assignment/{aId}/{uId}")
    public AssignmentResult getAssignmentResultByAssignmentIdAndUserId(@PathVariable("aId") long aId, @PathVariable("uId") long uId) {
        User user = new User();
        user.setId(uId);
        Assignment assignment = new Assignment();
        assignment.setaId(aId);
        return this.assignmentResultService.getAssignmentResultByAssignmentAndUser(assignment, user);
    }

//    Get All List Of AssignmentsResult of an Assignment
    @GetMapping("/assignments/{aId}")
    public List<AssignmentResult> getAssignmentResultByAssignment(@PathVariable("aId") long aId){
        Assignment assignment = new Assignment();
        assignment.setaId(aId);
        return this.assignmentResultService.getAllAssignmentResultByAssignment(assignment);
    }

    @DeleteMapping("/assignment/{arId}")
    public void deleteAssignmentResult(@PathVariable("arId") long arId){
        this.assignmentResultService.deleteAssignmentResult(arId);
    }




    //    LABTASK OPERATIONS  //
    //    User Submit LabTask
    @PostMapping("/labtask")
    public LabTaskResult submitLabTask(@RequestBody LabTask labTask, Principal principal) {
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        LabTaskResult labTaskResult = new LabTaskResult(labTask,user,labTask.getLabAnswer());
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        labTaskResult.setDate(dateFormat.format(date));
        return this.labTaskResultService.saveLabTaskResult(labTaskResult);
    }

    //    Mentor Marking the LabTask
    @PutMapping("/labtask/marks")
    public LabTaskResult submitLabTaskResult(@RequestBody LabTaskResult labTaskResult) {
        return this.labTaskResultService.assignMarksLabTaskResult(labTaskResult);
    }

    //    Getting LabTask Result...
    @GetMapping("/labtask/{labId}")
    public LabTaskResult getLabTaskResultByLabTaskAndUser(@PathVariable("labId") long labId, Principal principal) {
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        LabTask labTask = new LabTask();
        labTask.setLabId(labId);
        return this.labTaskResultService.getLabTaskResultByLabTaskAndUser(labTask, user);
    }

    @GetMapping("/labtask/{lId}/{uId}")
    public LabTaskResult getLabTaskResultByLabTaskIdAndUserId(@PathVariable("lId") long lId, @PathVariable("uId") long uId) {
        User user = new User();
        user.setId(uId);
        LabTask labTask = new LabTask();
        labTask.setLabId(lId);
        return this.labTaskResultService.getLabTaskResultByLabTaskAndUser(labTask, user);
    }


//    Get All List Of LabTasksResult of an LabTask
    @GetMapping("/labtasks/{labId}")
    public List<LabTaskResult> getLabTaskResultByLabTask(@PathVariable("labId") long labId){
        LabTask labTask = new LabTask();
        labTask.setLabId(labId);
        return this.labTaskResultService.getAllLabTaskResultByLabTask(labTask);
    }
    @DeleteMapping("/labtask/{lrId}")
    public void deleteLabTaskResult(@PathVariable("lrId") long lrId){
        this.labTaskResultService.deleteLabTaskResult(lrId);
    }
}

package com.codeventure.payload;

import com.codeventure.entities.result.AssignmentResult;
import com.codeventure.entities.result.LabTaskResult;
import com.codeventure.entities.result.QuizResult;

import java.util.List;

public class CountSubmittedTasks {

    private List<AssignmentResult> assignmentResults;
    private  List<LabTaskResult> labTaskResults;
    private List<QuizResult> quizResults;

    public CountSubmittedTasks() {
    }

    public CountSubmittedTasks(List<AssignmentResult> assignmentResults, List<LabTaskResult> labTaskResults, List<QuizResult> quizResults) {
        this.assignmentResults = assignmentResults;
        this.labTaskResults = labTaskResults;
        this.quizResults = quizResults;
    }

    public List<AssignmentResult> getAssignmentResults() {
        return assignmentResults;
    }

    public void setAssignmentResults(List<AssignmentResult> assignmentResults) {
        this.assignmentResults = assignmentResults;
    }

    public List<LabTaskResult> getLabTaskResults() {
        return labTaskResults;
    }

    public void setLabTaskResults(List<LabTaskResult> labTaskResults) {
        this.labTaskResults = labTaskResults;
    }

    public List<QuizResult> getQuizResults() {
        return quizResults;
    }

    public void setQuizResults(List<QuizResult> quizResults) {
        this.quizResults = quizResults;
    }

    @Override
    public String toString() {
        return "CountSubmittedTasks{" +
                "assignmentResults=" + assignmentResults +
                ", labTaskResults=" + labTaskResults +
                ", quizResults=" + quizResults +
                '}';
    }
}

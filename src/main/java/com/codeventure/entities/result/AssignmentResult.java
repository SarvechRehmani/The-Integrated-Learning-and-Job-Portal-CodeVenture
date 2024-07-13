package com.codeventure.entities.result;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Assignment;
import jakarta.persistence.*;

@Entity
public class AssignmentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long assignmentResultId;


    @ManyToOne(fetch = FetchType.EAGER)
    private Assignment assignment;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(length = 10000)
    private String assignmentAnswer;
    private double gotMarks;

    private String date;

    public AssignmentResult() {
    }

    public AssignmentResult(Assignment assignment, User user, String assignmentAnswer) {
        this.assignment = assignment;
        this.user = user;
        this.assignmentAnswer = assignmentAnswer;
    }

    public AssignmentResult(Assignment assignment, User user, String assignmentAnswer, double gotMarks) {
        this.assignment = assignment;
        this.user = user;
        this.assignmentAnswer = assignmentAnswer;
        this.gotMarks = gotMarks;
    }

    public AssignmentResult(Assignment assignment, User user, String assignmentAnswer, double gotMarks, String date) {
        this.assignment = assignment;
        this.user = user;
        this.assignmentAnswer = assignmentAnswer;
        this.gotMarks = gotMarks;
        this.date = date;
    }

    public AssignmentResult(long assignmentResultId, Assignment assignment, User user, String assignmentAnswer, double gotMarks) {
        this.assignmentResultId = assignmentResultId;
        this.assignment = assignment;
        this.user = user;
        this.assignmentAnswer = assignmentAnswer;
        this.gotMarks = gotMarks;
    }

    public long getAssignmentResultId() {
        return assignmentResultId;
    }

    public void setAssignmentResultId(long assignmentResultId) {
        this.assignmentResultId = assignmentResultId;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAssignmentAnswer() {
        return assignmentAnswer;
    }

    public void setAssignmentAnswer(String assignmentAnswer) {
        this.assignmentAnswer = assignmentAnswer;
    }

    public double getGotMarks() {
        return gotMarks;
    }

    public void setGotMarks(double gotMarks) {
        this.gotMarks = gotMarks;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AssignmentResult{" +
                "assignmentResultId=" + assignmentResultId +
                ", assignment=" + assignment +
                ", user=" + user +
                ", assignmentAnswer='" + assignmentAnswer + '\'' +
                ", gotMarks=" + gotMarks +
                ", date='" + date + '\'' +
                '}';
    }
}

package com.codeventure.entities.course;

import com.codeventure.entities.result.QuizResult;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long qId;
    private String qTitle;
    @Column(length = 5000)
    private String qDescription;
    private long maxMarks;
    private long numberOfQuestions;
    @OneToOne(fetch = FetchType.EAGER)
    private Lecture lecture;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "quiz", orphanRemoval = true)
    @JsonIgnore
    private List<QuizResult> quizResult  = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "quiz", orphanRemoval = true)
    @JsonIgnore
    private List<Question> questions;

    public Quiz() {
    }

    public Quiz(long qId, String qTitle, String qDescription, long maxMarks, long numberOfQuestions, Lecture lecture, List<Question> questions) {
        this.qId = qId;
        this.qTitle = qTitle;
        this.qDescription = qDescription;
        this.maxMarks = maxMarks;
        this.numberOfQuestions = numberOfQuestions;
        this.lecture = lecture;
        this.questions = questions;
    }

    public long getqId() {
        return qId;
    }

    public void setqId(long qId) {
        this.qId = qId;
    }

    public String getqTitle() {
        return qTitle;
    }

    public void setqTitle(String qTitle) {
        this.qTitle = qTitle;
    }

    public String getqDescription() {
        return qDescription;
    }

    public void setqDescription(String qDescription) {
        this.qDescription = qDescription;
    }

    public long getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(long maxMarks) {
        this.maxMarks = maxMarks;
    }

    public long getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(long numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<QuizResult> getQuizResult() {
        return quizResult;
    }

    public void setQuizResult(List<QuizResult> quizResult) {
        this.quizResult = quizResult;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "qId=" + qId +
                ", qTitle='" + qTitle + '\'' +
                ", qDescription='" + qDescription + '\'' +
                ", maxMarks=" + maxMarks +
                ", numberOfQuestions=" + numberOfQuestions +
                ", lecture=" + lecture +
                ", quizResult=" + quizResult +
                ", questions=" + questions +
                '}';
    }
}

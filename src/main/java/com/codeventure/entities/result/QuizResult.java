package com.codeventure.entities.result;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Quiz;
import jakarta.persistence.*;

@Entity
public class QuizResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long QuizResultId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private long attempted;
    private double gotMarks;
    private long correctAnswers;

    private String date;

    public QuizResult() {
    }

    public QuizResult(Quiz quiz, User user, long attempted, double gotMarks, long correctAnswers) {
        this.quiz = quiz;
        this.user = user;
        this.attempted = attempted;
        this.gotMarks = gotMarks;
        this.correctAnswers = correctAnswers;
    }

    public QuizResult(Quiz quiz, User user, long attempted, double gotMarks, long correctAnswers, String date) {
        this.quiz = quiz;
        this.user = user;
        this.attempted = attempted;
        this.gotMarks = gotMarks;
        this.correctAnswers = correctAnswers;
        this.date = date;
    }

    public QuizResult(long quizResultId, Quiz quiz, User user, long attempted, double gotMarks, long correctAnswers) {
        QuizResultId = quizResultId;
        this.quiz = quiz;
        this.user = user;
        this.attempted = attempted;
        this.gotMarks = gotMarks;
        this.correctAnswers = correctAnswers;
    }

    public long getQuizResultId() {
        return QuizResultId;
    }

    public void setQuizResultId(long quizResultId) {
        QuizResultId = quizResultId;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getAttempted() {
        return attempted;
    }

    public void setAttempted(long attempted) {
        this.attempted = attempted;
    }

    public double getGotMarks() {
        return gotMarks;
    }

    public void setGotMarks(double gotMarks) {
        this.gotMarks = gotMarks;
    }

    public long getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(long correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "QuizResult{" +
                "QuizResultId=" + QuizResultId +
                ", quiz=" + quiz +
                ", user=" + user +
                ", attempted=" + attempted +
                ", gotMarks=" + gotMarks +
                ", correctAnswers=" + correctAnswers +
                ", date='" + date + '\'' +
                '}';
    }
}

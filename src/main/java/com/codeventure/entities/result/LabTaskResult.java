package com.codeventure.entities.result;

import com.codeventure.entities.User;
import com.codeventure.entities.course.LabTask;
import jakarta.persistence.*;

@Entity
public class LabTaskResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long labTaskResultId;

    @ManyToOne(fetch = FetchType.EAGER)
    private LabTask labTask;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(length = 10000)
    private String labTaskAnswer;
    private double gotMarks;

    private String date;

    public LabTaskResult() {
    }

    public LabTaskResult(LabTask labTask, User user, String labTaskAnswer) {
        this.labTask = labTask;
        this.user = user;
        this.labTaskAnswer = labTaskAnswer;
    }

    public LabTaskResult(LabTask labTask, User user, String labTaskAnswer, double gotMarks) {
        this.labTask = labTask;
        this.user = user;
        this.labTaskAnswer = labTaskAnswer;
        this.gotMarks = gotMarks;
    }

    public LabTaskResult(LabTask labTask, User user, String labTaskAnswer, double gotMarks, String date) {
        this.labTask = labTask;
        this.user = user;
        this.labTaskAnswer = labTaskAnswer;
        this.gotMarks = gotMarks;
        this.date = date;
    }

    public LabTaskResult(long labTaskResultId, LabTask labTask, User user, String labTaskAnswer, double gotMarks) {
        this.labTaskResultId = labTaskResultId;
        this.labTask = labTask;
        this.user = user;
        this.labTaskAnswer = labTaskAnswer;
        this.gotMarks = gotMarks;
    }

    public long getLabTaskResultId() {
        return labTaskResultId;
    }

    public void setLabTaskResultId(long labTaskResultId) {
        this.labTaskResultId = labTaskResultId;
    }

    public LabTask getLabTask() {
        return labTask;
    }

    public void setLabTask(LabTask labTask) {
        this.labTask = labTask;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLabTaskAnswer() {
        return labTaskAnswer;
    }

    public void setLabTaskAnswer(String labTaskAnswer) {
        this.labTaskAnswer = labTaskAnswer;
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
        return "LabTaskResult{" +
                "labTaskResultId=" + labTaskResultId +
                ", labTask=" + labTask +
                ", user=" + user +
                ", labTaskAnswer='" + labTaskAnswer + '\'' +
                ", gotMarks=" + gotMarks +
                ", data='" + date + '\'' +
                '}';
    }
}

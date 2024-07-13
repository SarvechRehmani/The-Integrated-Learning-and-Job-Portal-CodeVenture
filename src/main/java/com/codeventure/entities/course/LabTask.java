package com.codeventure.entities.course;


import com.codeventure.entities.result.LabTaskResult;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class LabTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long labId;
    @Column(length = 5000)
    private String labContent;
    private long maxMarks;

    @Transient
    private String labAnswer;
    @OneToOne(fetch = FetchType.EAGER)
    private Lecture lecture;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "labTask", orphanRemoval = true)
    @JsonIgnore
    private List<LabTaskResult> labTaskResults  = new ArrayList<>();

    public LabTask() {
    }

    public LabTask(long labId, String labContent, long maxMarks, Lecture lecture) {
        this.labId = labId;
        this.labContent = labContent;
        this.maxMarks = maxMarks;
        this.lecture = lecture;
    }
    public LabTask(long labId, String labContent, long maxMarks, String labAnswer, Lecture lecture) {
        this.labId = labId;
        this.labContent = labContent;
        this.maxMarks = maxMarks;
        this.labAnswer = labAnswer;
        this.lecture = lecture;
    }

    public long getLabId() {
        return labId;
    }

    public void setLabId(long labId) {
        this.labId = labId;
    }

    public String getLabContent() {
        return labContent;
    }

    public void setLabContent(String labContent) {
        this.labContent = labContent;
    }

    public long getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(long maxMarks) {
        this.maxMarks = maxMarks;
    }

    public String getLabAnswer() {
        return labAnswer;
    }

    public void setLabAnswer(String labAnswer) {
        this.labAnswer = labAnswer;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public List<LabTaskResult> getLabTaskResults() {
        return labTaskResults;
    }

    public void setLabTaskResults(List<LabTaskResult> labTaskResults) {
        this.labTaskResults = labTaskResults;
    }
}

package com.codeventure.entities.course;

import com.codeventure.entities.result.AssignmentResult;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long aId;
    @Column(length = 5000)
    private String aContent;
    private long maxMarks;

    @Transient
    private String aAnswer;

    @OneToOne(fetch = FetchType.EAGER)
    private Lecture lecture;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "assignment", orphanRemoval = true)
    @JsonIgnore
    private List<AssignmentResult> assignmentResults  = new ArrayList<>();

    public Assignment() {
    }

    public Assignment(long aId, String aContent, long maxMarks, Lecture lecture) {
        this.aId = aId;
        this.aContent = aContent;
        this.maxMarks = maxMarks;
        this.lecture = lecture;
    }

    public long getaId() {
        return aId;
    }

    public void setaId(long aId) {
        this.aId = aId;
    }

    public String getaContent() {
        return aContent;
    }

    public void setaContent(String aContent) {
        this.aContent = aContent;
    }

    public long getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(long maxMarks) {
        this.maxMarks = maxMarks;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public List<AssignmentResult> getAssignmentResults() {
        return assignmentResults;
    }

    public void setAssignmentResults(List<AssignmentResult> assignmentResults) {
        this.assignmentResults = assignmentResults;
    }

    public String getaAnswer() {
        return aAnswer;
    }

    public void setaAnswer(String aAnswer) {
        this.aAnswer = aAnswer;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "aId=" + aId +
                ", aContent='" + aContent + '\'' +
                ", maxMarks=" + maxMarks +
                ", aAnswer='" + aAnswer + '\'' +
                ", lecture=" + lecture +
                ", assignmentResults=" + assignmentResults +
                '}';
    }
}

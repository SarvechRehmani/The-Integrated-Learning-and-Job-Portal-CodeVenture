package com.codeventure.entities.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long lId;
    private long lNo;
    private String lTitle;
    @Column(length = 5000)
    private String lDescription;
    private String lVideo;
    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "lecture", orphanRemoval = true)
    @JsonIgnore
    private Assignment assignment;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "lecture", orphanRemoval = true)
    @JsonIgnore
    private LabTask labTask;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "lecture", orphanRemoval = true)
    @JsonIgnore
    private Quiz quiz;

    public Lecture() {
    }

    public Lecture(long lId, long lNo, String lTitle, String lDescription, String lVideo, Course course, Assignment assignment, LabTask labTask, Quiz quiz) {
        this.lId = lId;
        this.lNo = lNo;
        this.lTitle = lTitle;
        this.lDescription = lDescription;
        this.lVideo = lVideo;
        this.course = course;
        this.assignment = assignment;
        this.labTask = labTask;
        this.quiz = quiz;
    }

    public long getlId() {
        return lId;
    }

    public void setlId(long lId) {
        this.lId = lId;
    }

    public long getlNo() {
        return lNo;
    }

    public void setlNo(long lNo) {
        this.lNo = lNo;
    }

    public String getlTitle() {
        return lTitle;
    }

    public void setlTitle(String lTitle) {
        this.lTitle = lTitle;
    }

    public String getlDescription() {
        return lDescription;
    }

    public void setlDescription(String lDescription) {
        this.lDescription = lDescription;
    }

    public String getlVideo() {
        return lVideo;
    }

    public void setlVideo(String lVideo) {
        this.lVideo = lVideo;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public LabTask getLabTask() {
        return labTask;
    }

    public void setLabTask(LabTask labTask) {
        this.labTask = labTask;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "lId=" + lId +
                ", lNo=" + lNo +
                ", lTitle='" + lTitle + '\'' +
                ", lDescription='" + lDescription + '\'' +
                ", lVideo='" + lVideo + '\'' +
                ", course=" + course +
                ", assignment=" + assignment +
                ", labTask=" + labTask +
                ", quiz=" + quiz +
                '}';
    }
}

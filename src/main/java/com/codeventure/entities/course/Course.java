package com.codeventure.entities.course;

import com.codeventure.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cId;
    private String cTitle;
    @Column(length = 1500)
    private String cDescription;

    private String field;
    private long totalLectures;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "course", orphanRemoval = true)
    @JsonIgnore
    private List<Lecture> lectures = new ArrayList<>();

    public Course() {
    }

    public Course(long cId, String cTitle, String cDescription, String field, long totalLectures, User user, List<Lecture> lectures) {
        this.cId = cId;
        this.cTitle = cTitle;
        this.cDescription = cDescription;
        this.field = field;
        this.totalLectures = totalLectures;
        this.user = user;
        this.lectures = lectures;
    }

    public long getcId() {
        return cId;
    }

    public void setcId(long cId) {
        this.cId = cId;
    }

    public String getcTitle() {
        return cTitle;
    }

    public void setcTitle(String cTitle) {
        this.cTitle = cTitle;
    }

    public String getcDescription() {
        return cDescription;
    }

    public void setcDescription(String cDescription) {
        this.cDescription = cDescription;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public long getTotalLectures() {
        return totalLectures;
    }

    public void setTotalLectures(long totalLectures) {
        this.totalLectures = totalLectures;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cId=" + cId +
                ", cTitle='" + cTitle + '\'' +
                ", cDescription='" + cDescription + '\'' +
                ", totalLectures=" + totalLectures +
                ", user=" + user +
                ", lectures=" + lectures +
                '}';
    }
}

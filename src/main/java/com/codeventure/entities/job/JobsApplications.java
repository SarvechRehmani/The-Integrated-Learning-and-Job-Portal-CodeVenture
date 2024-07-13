package com.codeventure.entities.job;

import com.codeventure.entities.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class JobsApplications {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long jobAId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Job job;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private String resume;

    private String userExperience;
    private String userEducation;
    private String userSkill;

    private String date;

    private String status = "Pending";

    public JobsApplications() {
    }

    public JobsApplications(Job job, User user, String resume) {
        this.job = job;
        this.user = user;
        this.resume = resume;
    }

    public JobsApplications(Job job, User user, String resume, String status) {
        this.job = job;
        this.user = user;
        this.resume = resume;
        this.status = status;
    }
    public JobsApplications(long jobAId, Job job, User user, String resume, String status) {
        this.jobAId = jobAId;
        this.job = job;
        this.user = user;
        this.resume = resume;
        this.status = status;
    }

    public JobsApplications(Job job, User user, String resume, String userExperience, String userEducation, String userSkill, String date, String status) {
        this.job = job;
        this.user = user;
        this.resume = resume;
        this.userExperience = userExperience;
        this.userEducation = userEducation;
        this.userSkill = userSkill;
        this.date = date;
        this.status = status;
    }

    public JobsApplications(Job job, User user, String resume, String userExperience, String userEducation, String userSkill) {
        this.job = job;
        this.user = user;
        this.resume = resume;
        this.userExperience = userExperience;
        this.userEducation = userEducation;
        this.userSkill = userSkill;
    }

    public JobsApplications(long jobAId, Job job, User user, String resume, String userExperience, String userEducation, String userSkill, String status) {
        this.jobAId = jobAId;
        this.job = job;
        this.user = user;
        this.resume = resume;
        this.userExperience = userExperience;
        this.userEducation = userEducation;
        this.userSkill = userSkill;
        this.status = status;
    }

    public long getJobAId() {
        return jobAId;
    }

    public void setJobAId(long jobAId) {
        this.jobAId = jobAId;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserExperience() {
        return userExperience;
    }

    public void setUserExperience(String userExperience) {
        this.userExperience = userExperience;
    }

    public String getUserEducation() {
        return userEducation;
    }

    public void setUserEducation(String userEducation) {
        this.userEducation = userEducation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserSkill() {
        return userSkill;
    }

    public void setUserSkill(String userSkill) {
        this.userSkill = userSkill;
    }

    @Override
    public String toString() {
        return "JobsApplications{" +
                "jobAId=" + jobAId +
                ", job=" + job +
                ", user=" + user +
                ", resume='" + resume + '\'' +
                ", userExperience='" + userExperience + '\'' +
                ", userEducation='" + userEducation + '\'' +
                ", userSkill='" + userSkill + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}

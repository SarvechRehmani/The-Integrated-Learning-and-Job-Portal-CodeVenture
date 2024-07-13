package com.codeventure.payload;

public class CountCoursesDetails {

    private long courses;
    private long Lectures;
    private long Assignments;
    private long labTasks;
    private long Quizzes;

    public CountCoursesDetails() {
    }

    public CountCoursesDetails(long courses, long lectures, long assignments, long labTasks, long quizzes) {
        this.courses = courses;
        Lectures = lectures;
        Assignments = assignments;
        this.labTasks = labTasks;
        Quizzes = quizzes;
    }

    public long getCourses() {
        return courses;
    }

    public void setCourses(long courses) {
        this.courses = courses;
    }

    public long getLectures() {
        return Lectures;
    }

    public void setLectures(long lectures) {
        Lectures = lectures;
    }

    public long getAssignments() {
        return Assignments;
    }

    public void setAssignments(long assignments) {
        Assignments = assignments;
    }

    public long getLabTasks() {
        return labTasks;
    }

    public void setLabTasks(long labTasks) {
        this.labTasks = labTasks;
    }

    public long getQuizzes() {
        return Quizzes;
    }

    public void setQuizzes(long quizzes) {
        Quizzes = quizzes;
    }

    @Override
    public String toString() {
        return "CountCoursesDetails{" +
                "courses='" + courses + '\'' +
                ", Lectures='" + Lectures + '\'' +
                ", Assignments='" + Assignments + '\'' +
                ", labTasks='" + labTasks + '\'' +
                ", Quizzes='" + Quizzes + '\'' +
                '}';
    }
}

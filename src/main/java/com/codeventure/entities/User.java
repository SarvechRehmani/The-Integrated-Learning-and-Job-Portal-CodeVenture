package com.codeventure.entities;

import com.codeventure.entities.course.Course;
import com.codeventure.entities.job.Job;
import com.codeventure.entities.job.JobsApplications;
import com.codeventure.entities.result.AssignmentResult;
import com.codeventure.entities.result.LabTaskResult;
import com.codeventure.entities.result.QuizResult;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    private String field;
    private boolean enabled = true;
    private String profile = "Default.png";

    @Column(length = 1500)
    private String bio;

    private String address;

    private String date;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    @JsonIgnore
    private List<Course> courses = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    @JsonIgnore
    private List<QuizResult> quizResult = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    @JsonIgnore
    private List<AssignmentResult> assignmentResults = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    @JsonIgnore
    private List<LabTaskResult> labTaskResults = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    @JsonIgnore
    private List<Job> jobs = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    @JsonIgnore
    private List<JobsApplications> jobsApplications;

    @Transient
    private String checkRole;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "user")
    @JsonIgnore
    private Set<UserRole> usersRoles = new HashSet<>();


    public User(){
    }

    public User(long id, String username, String password, String firstName, String lastName, String email, String phone, String field, boolean enabled, String profile, String bio, String address, List<Course> courses, List<QuizResult> quizResult, List<AssignmentResult> assignmentResults, List<LabTaskResult> labTaskResults, List<Job> jobs, List<JobsApplications> jobsApplications, String checkRole, Set<UserRole> usersRoles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.field = field;
        this.enabled = enabled;
        this.profile = profile;
        this.bio = bio;
        this.address = address;
        this.courses = courses;
        this.quizResult = quizResult;
        this.assignmentResults = assignmentResults;
        this.labTaskResults = labTaskResults;
        this.jobs = jobs;
        this.jobsApplications = jobsApplications;
        this.checkRole = checkRole;
        this.usersRoles = usersRoles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Set<UserRole> getUsersRoles() {
        return usersRoles;
    }

    public void setUsersRoles(Set<UserRole> usersRoles) {
        this.usersRoles = usersRoles;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCheckRole() {
        return checkRole;
    }

    public void setCheckRole(String checkRole) {
        this.checkRole = checkRole;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<QuizResult> getQuizResult() {
        return quizResult;
    }

    public void setQuizResult(List<QuizResult> quizResult) {
        this.quizResult = quizResult;
    }

    public List<AssignmentResult> getAssignmentResults() {
        return assignmentResults;
    }

    public void setAssignmentResults(List<AssignmentResult> assignmentResults) {
        this.assignmentResults = assignmentResults;
    }

    public List<LabTaskResult> getLabTaskResults() {
        return labTaskResults;
    }

    public void setLabTaskResults(List<LabTaskResult> labTaskResults) {
        this.labTaskResults = labTaskResults;
    }

    public List<JobsApplications> getJobsApplications() {
        return jobsApplications;
    }

    public void setJobsApplications(List<JobsApplications> jobsApplications) {
        this.jobsApplications = jobsApplications;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", field='" + field + '\'' +
                ", enabled=" + enabled +
                ", profile='" + profile + '\'' +
                ", bio='" + bio + '\'' +
                ", address='" + address + '\'' +
                ", date='" + date + '\'' +
                ", courses=" + courses +
                ", quizResult=" + quizResult +
                ", assignmentResults=" + assignmentResults +
                ", labTaskResults=" + labTaskResults +
                ", jobs=" + jobs +
                ", jobsApplications=" + jobsApplications +
                ", checkRole='" + checkRole + '\'' +
                ", usersRoles=" + usersRoles +
                '}';
    }

    //  ------------UserDetails implements--------------
    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<Authority> set = new HashSet<>();

        this.usersRoles.forEach(userRole -> {
            set.add(new Authority(userRole.getRole().getRoleName()));
        });

        return set;
    }
//    ------------UserDetails implements--------------
}

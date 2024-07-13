package com.codeventure.controller;

import com.codeventure.entities.Role;
import com.codeventure.entities.User;
import com.codeventure.entities.UserRole;
import com.codeventure.entities.course.Course;
import com.codeventure.entities.course.Lecture;
import com.codeventure.entities.job.Job;
import com.codeventure.entities.job.JobsApplications;
import com.codeventure.entities.result.AssignmentResult;
import com.codeventure.entities.result.LabTaskResult;
import com.codeventure.entities.result.QuizResult;
import com.codeventure.payload.*;
import com.codeventure.repo.UserRoleRepository;
import com.codeventure.services.RoleService;
import com.codeventure.services.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin("*")
public class DashboardController {


    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AssignmentResultServiceImpl assignmentResultService;
    @Autowired
    private LabTaskResultServiceImpl labTaskResultService;
    @Autowired
    private QuizResultServiceImpl quizResultService;
    @Autowired
    private CourseServiceImpl courseService;
    @Autowired
    private JobServiceImpl jobService;

    //    FOR ADMIN DASHBOARD
    @GetMapping("count/users")
    public CountUsers countUsers() {
        Role normalRole = this.roleService.getRole(23);
        List<UserRole> normalUsers = this.userRoleRepository.findByRole(normalRole);
        Role mentorRole = this.roleService.getRole(24);
        List<UserRole> mentorUsers = this.userRoleRepository.findByRole(mentorRole);
        Role companyRole = this.roleService.getRole(25);
        List<UserRole> companyUsers = this.userRoleRepository.findByRole(companyRole);

        return new CountUsers(normalUsers.size(), mentorUsers.size(), companyUsers.size());
    }

    @GetMapping("users")
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    //    FOR USER DASHBOARD
    @GetMapping("count/tasks")
    public CountTasks countTasks(Principal principal) {
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        List<AssignmentResult> assignmentResults = this.assignmentResultService.getAssignmentResultsByUser(user);
        List<LabTaskResult> labTaskResults = this.labTaskResultService.getLabTaskResultByUser(user);
        List<QuizResult> quizResults = this.quizResultService.getQuizResultByUser(user);

        return new CountTasks(assignmentResults, labTaskResults, quizResults);
    }

    //    FOR MENTOR DASHBOARD
    @GetMapping("count/courses/details")
    public CountCoursesDetails countCoursesDetails(Principal principal) {
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        List<Course> courses = this.courseService.getCoursesByUser(user);
        long lectures = 0;
        long assignments = 0;
        long labTasks = 0;
        long quizzes = 0;
        for (Course course : courses) {
            lectures = lectures + course.getLectures().size();
            for (Lecture lecture : course.getLectures()) {
                if (lecture.getAssignment() != null) {
                    assignments++;
                }
                if (lecture.getLabTask() != null) {
                    labTasks++;
                }
                if (lecture.getQuiz() != null) {
                    quizzes++;
                }
            }
        }
        return new CountCoursesDetails(courses.size(), lectures, assignments, labTasks, quizzes);
    }

    @GetMapping("count/submitted/tasks")
    public CountSubmittedTasks countSubmittedTasks(Principal principal) {
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        List<Course> courses = this.courseService.getCoursesByUser(user);

        List<AssignmentResult> assignmentResults = new ArrayList<>();
        List<LabTaskResult> labTaskResults = new ArrayList<>();
        List<QuizResult> quizResults = new ArrayList<>();

        for (Course course : courses) {
            for (Lecture lecture : course.getLectures()) {
                if (lecture.getAssignment() != null) {
                    assignmentResults.addAll(lecture.getAssignment().getAssignmentResults());
                }
                if (lecture.getLabTask() != null) {
                    labTaskResults.addAll(lecture.getLabTask().getLabTaskResults());
                }
                if (lecture.getQuiz() != null) {
                    quizResults.addAll(lecture.getQuiz().getQuizResult());
                }
            }
        }
        return new CountSubmittedTasks(assignmentResults, labTaskResults, quizResults);
    }

    //    FOR COMPANY
    @GetMapping("count/jobs/jobsApplications")
    public CountJobsAndJobsApplications countJobsAndJobsApplications(Principal principal) {
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        List<Job> jobs =this.jobService.getJobsByUser(user);
        List<JobsApplications> jobsApplications = new ArrayList<>();
        for (Job job: jobs){
            jobsApplications.addAll(job.getJobsApplications());
        }
        return new CountJobsAndJobsApplications(jobs.size(), jobsApplications);
    }
}

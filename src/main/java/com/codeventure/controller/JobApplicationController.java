package com.codeventure.controller;

import com.codeventure.entities.User;
import com.codeventure.entities.job.Job;
import com.codeventure.entities.job.JobsApplications;
import com.codeventure.services.impl.JobApplicationServiceImpl;
import com.codeventure.services.impl.JobServiceImpl;
import com.codeventure.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/job-application")
@CrossOrigin("*")
public class JobApplicationController {
    @Autowired
    private JobApplicationServiceImpl jobApplicationService;

    @Autowired
    private JobServiceImpl jobService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/")
    public JobsApplications saveJobsApplications(@RequestBody JobsApplications jobsApplications){
        return this.jobApplicationService.saveJobsApplications(jobsApplications);
    }

    @PutMapping("/")
    public JobsApplications updateJobsApplications(@RequestBody JobsApplications jobsApplications){
        return this.jobApplicationService.updateJobsApplications(jobsApplications);
    }

    @DeleteMapping("/{jobAId}")
    public void deleteJobsApplications(@PathVariable("jobAId") long jobAid){
        this.jobApplicationService.deleteJobApplication(jobAid);
    }
    @GetMapping("/{jobAId}")
    public JobsApplications getJobsApplicationsById(@PathVariable("jobAId") long jobAid){
        return this.jobApplicationService.getSingleJobsApplications(jobAid);
    }
    @GetMapping("/user/{userId}")
    public List<JobsApplications> getJobsApplicationsByUser(@PathVariable("userId") long userId){
        User user = new User();
        user.setId(userId);
        return this.jobApplicationService.getJobsApplicationsByUser(user);
    }

    @GetMapping("/job/{jobId}")
    public List<JobsApplications> getJobsApplicationsByJob(@PathVariable("jobId") long jobId){
        Job job = new Job();
        job.setjId(jobId);
        return this.jobApplicationService.getJobsApplicationsByJob(job);
    }

    @GetMapping("/job/user/{jobId}/{userId}")
    public JobsApplications getJobsApplicationsByJobAndUser(@PathVariable("jobId") long jobId, @PathVariable("userId") long userId){
        Job job = new Job();
        job.setjId(jobId);
        User user = new User();
        user.setId(userId);
        return this.jobApplicationService.getJobsApplicationsByJobAndUser(job, user);
    }

    @GetMapping("/application/user/job/{jobId}")
    public List<JobsApplications> getJobsApplicationsByJobIdOfUserJob(@PathVariable("jobId") long jobId, Principal principal){
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        return this.jobApplicationService.getJobsApplicationsByJobIdOfUserJob(jobId, user);
    }

    @GetMapping("application/user")
    public List<JobsApplications> getJobsApplicationsByUser(Principal principal){
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        List<Job> jobs = this.jobService.getJobsByUser(user);
        List<JobsApplications> jobsApplications = new ArrayList<>();
        for(Job job: jobs){
            jobsApplications.addAll(job.getJobsApplications());
        }
        return jobsApplications;
    }
}

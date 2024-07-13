package com.codeventure.services.impl;

import com.codeventure.entities.User;
import com.codeventure.entities.job.Job;
import com.codeventure.entities.job.JobsApplications;
import com.codeventure.helper.DeleteService;
import com.codeventure.repo.job.JobApplicationsRepository;
import com.codeventure.services.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    @Autowired
    private JobApplicationsRepository jobApplicationsRepository;

    @Autowired
    private DeleteService deleteService;

    @Autowired
    private JobServiceImpl jobService;
    @Override
    public JobsApplications saveJobsApplications(JobsApplications jobsApplications) {
        return this.jobApplicationsRepository.save(jobsApplications);
    }

    @Override
    public JobsApplications updateJobsApplications(JobsApplications jobsApplications) {
        JobsApplications jobsApplications1 = this.getSingleJobsApplications(jobsApplications.getJobAId());
        jobsApplications1.setStatus(jobsApplications.getStatus());
        return this.jobApplicationsRepository.save(jobsApplications1);
    }

    @Override
    public void deleteJobApplication(long jobAId){
        this.deleteService.deleteJobApplications(jobAId);
    }

    @Override
    public JobsApplications getSingleJobsApplications(long jobAId) {
        Optional<JobsApplications> optionalJobApplication = this.jobApplicationsRepository.findById(jobAId);
        if (optionalJobApplication.isPresent()) {
            return optionalJobApplication.get();
        } else {
            throw new NoSuchElementException("No JobsApplications found with id: " + jobAId);
        }
    }

    @Override
    public List<JobsApplications> getJobsApplicationsByUser(User user) {
        return this.jobApplicationsRepository.findByUser(user);
    }

    @Override
    public List<JobsApplications> getJobsApplicationsByJob(Job job) {
        return this.jobApplicationsRepository.findByJob(job);
    }

    @Override
    public JobsApplications getJobsApplicationsByJobAndUser(Job job, User user) {
        return this.jobApplicationsRepository.findByJobAndUser(job, user);
    }

    public List<JobsApplications> getJobsApplicationsByJobIdOfUserJob(long jobId, User user){
        List<Job> jobs = this.jobService.getJobsByUser(user);
        List<JobsApplications> jobsApplications = new ArrayList<>();
        for(Job job: jobs){
            if(job.getjId() == jobId){
                jobsApplications.addAll(job.getJobsApplications());
            }
        }
        return jobsApplications;
    }
}

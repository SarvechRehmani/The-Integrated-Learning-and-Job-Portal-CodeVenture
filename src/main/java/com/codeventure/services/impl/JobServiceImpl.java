package com.codeventure.services.impl;

import java.util.List;

import com.codeventure.helper.DeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeventure.entities.User;
import com.codeventure.entities.job.Job;

import com.codeventure.repo.course.JobRepository;
import com.codeventure.services.JobService;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private DeleteService deleteService;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public Job addJob(Job job) {
        return this.jobRepository.save(job);
    }

    @Override
    public Job updateJob(Job job) {
        Job job1 = this.getSingleJob(job.getjId());
        job.setJobsApplications(job1.getJobsApplications());
        return this.jobRepository.save(job);
    }

    @Override
    public void deleteJob(long jId){
        this.deleteService.deleteJob(jId);
    }

    @Override
    public Job getSingleJob(long id) {
        return this.jobRepository.findById(id).get();
    }

    @Override
    public List<Job> getAllJobs() {
        return this.jobRepository.findAll();
    }

    @Override
    public List<Job> getJobsByUser(User user) {
        return this.jobRepository.findByUser(user);
    }

    @Override
    public Job getSingleJobUser(long id, User user) {
        List<Job> jobs = this.getJobsByUser(user);
        for (Job job : jobs) {
            if (job.getjId() == id) {
                return job;
            }
        }
        return null;
    }
}

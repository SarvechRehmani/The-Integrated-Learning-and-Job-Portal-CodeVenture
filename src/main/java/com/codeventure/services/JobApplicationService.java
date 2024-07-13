package com.codeventure.services;

import com.codeventure.entities.User;
import com.codeventure.entities.job.Job;
import com.codeventure.entities.job.JobsApplications;

import java.util.List;

public interface JobApplicationService {

    public JobsApplications saveJobsApplications(JobsApplications jobsApplications);

    public JobsApplications updateJobsApplications(JobsApplications jobsApplications);

    public void deleteJobApplication(long jobAId) throws Exception;

    public JobsApplications getSingleJobsApplications(long jobAId);

    public List<JobsApplications> getJobsApplicationsByUser(User user);

    public List<JobsApplications> getJobsApplicationsByJob(Job job);
    public JobsApplications getJobsApplicationsByJobAndUser(Job job, User user);

}

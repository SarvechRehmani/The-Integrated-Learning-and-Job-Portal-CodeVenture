package com.codeventure.repo.job;

import com.codeventure.entities.User;
import com.codeventure.entities.job.Job;
import com.codeventure.entities.job.JobsApplications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationsRepository extends JpaRepository<JobsApplications, Long> {

    public List<JobsApplications> findByUser(User user);
    public List<JobsApplications> findByJob(Job job);
    public JobsApplications findByJobAndUser(Job job,User user);
}

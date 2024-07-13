package com.codeventure.services;

import java.util.List;

import com.codeventure.entities.User;

import com.codeventure.entities.job.Job;

public interface JobService {
	 public Job addJob(Job job);

	    public Job updateJob(Job job);

	    public void deleteJob(long jId) throws Exception;

	    public Job getSingleJob(long id);

	    public List<Job> getAllJobs();

	    public List<Job> getJobsByUser(User user);

	    public Job getSingleJobUser(long id, User user);
}

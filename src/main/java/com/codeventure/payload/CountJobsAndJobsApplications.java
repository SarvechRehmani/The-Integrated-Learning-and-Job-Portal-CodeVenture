package com.codeventure.payload;

import com.codeventure.entities.job.JobsApplications;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public class CountJobsAndJobsApplications {
    private long jobs;
    private List<JobsApplications> jobsApplications;

    public CountJobsAndJobsApplications() {
    }

    public CountJobsAndJobsApplications(long jobs, List<JobsApplications> jobsApplications) {
        this.jobs = jobs;
        this.jobsApplications = jobsApplications;
    }

    public long getJobs() {
        return jobs;
    }

    public void setJobs(long jobs) {
        this.jobs = jobs;
    }

    public List<JobsApplications> getJobsApplications() {
        return jobsApplications;
    }

    public void setJobsApplications(List<JobsApplications> jobsApplications) {
        this.jobsApplications = jobsApplications;
    }

    @Override
    public String toString() {
        return "CountJobsAndJobsApplications{" +
                "jobs=" + jobs +
                ", jobsApplications=" + jobsApplications +
                '}';
    }
}

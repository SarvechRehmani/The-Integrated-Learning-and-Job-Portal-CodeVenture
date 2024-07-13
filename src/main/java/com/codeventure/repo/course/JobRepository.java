package com.codeventure.repo.course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeventure.entities.User;

import com.codeventure.entities.job.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
    public List<Job> findByUser(User user);
}

package com.codeventure.controller;

import java.security.Principal;
import java.util.List;

import com.codeventure.entities.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeventure.entities.User;
import com.codeventure.entities.job.Job;
import com.codeventure.services.impl.JobServiceImpl;
import com.codeventure.services.impl.UserDetailsServiceImpl;

@RestController
@RequestMapping("/job")
@CrossOrigin("*")
public class JobController {
	

    @Autowired
    private JobServiceImpl jobService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/")
    public Job addJob(@RequestBody Job job, Principal principal){
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        job.setUser(user);
        return this.jobService.addJob(job);
    }
    @GetMapping("/showuser")
    public void showUser(Principal principal){
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        System.out.println(user);
       for (UserRole userRole: user.getUsersRoles()){
           System.out.println(userRole.getRole().getRoleName());
       }
    }

    @PutMapping("/")
    public Job updateJob(@RequestBody Job job, Principal principal){
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        job.setUser(user);
        return this.jobService.updateJob(job);
    }
    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable("id") long id){
        this.jobService.deleteJob(id);
    }

    @GetMapping("/{id}")
    public Job getSingleJob(@PathVariable("id") long id){
        return this.jobService.getSingleJob(id);
    }

    @GetMapping("/")
    public List<Job> getAllJobs(){
        return this.jobService.getAllJobs();
    }

    @GetMapping("/user")
    public List<Job> getJobsByUser(Principal principal){
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        return this.jobService.getJobsByUser(user);
    }

    @GetMapping("/user/{id}")
    public Job getSingleJobUser(@PathVariable("id") long id, Principal principal){
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        return this.jobService.getSingleJobUser(id,user);
    }

}

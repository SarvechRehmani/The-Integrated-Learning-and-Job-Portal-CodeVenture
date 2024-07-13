package com.codeventure.controller;

import com.codeventure.entities.User;
import com.codeventure.entities.course.LabTask;
import com.codeventure.entities.result.LabTaskResult;
import com.codeventure.services.impl.LabTaskResultServiceImpl;
import com.codeventure.services.impl.LabTaskServiceImpl;
import com.codeventure.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/labtask")
@CrossOrigin("*")
public class LabTaskController {

    @Autowired
    private LabTaskServiceImpl labTaskService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/")
    public LabTask addLabTask(@RequestBody LabTask labTask){
        return this.labTaskService.addLabTask(labTask);
    }

    @PutMapping("/")
    public LabTask updateLabTask(@RequestBody LabTask labTask){
        return this.labTaskService.updateLabTask(labTask);
    }

    @DeleteMapping("/{id}")
    public void deleteLabTask(@PathVariable("id") long id){
        this.labTaskService.deleteLabTask(id);
    }

    @GetMapping("/{id}")
    public LabTask getSingleLabTask(@PathVariable("id") long id){
        return this.labTaskService.getSingleLabTask(id);
    }

    @GetMapping("/lecture/{lId}")
    public LabTask getLabTaskByLecture(@PathVariable("lId") long lId){
        return this.labTaskService.getLabTaskByLecture(lId);
    }

    @GetMapping("/user")
    public List<LabTask> getLabTaskByUserCourses(Principal principal){
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        return  this.labTaskService.getLabTaskByUserCourses(user);
    }

    @GetMapping("/")
    public List<LabTask> getAllLabTasks(){
        return this.labTaskService.getAllLabTasks();
    }

    @GetMapping("/user/{id}")
    public LabTask getSingleLabTaskUser(@PathVariable("id") long id, Principal principal){
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        return this.labTaskService.getSingleLabTaskUser(id, user);
    }

    @GetMapping("/field/{field}")
    public List<LabTask> getLabTaskByField(@PathVariable("field") String field){
        return this.labTaskService.getLabTaskByField(field);
    }
}

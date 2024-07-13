package com.codeventure.controller;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Assignment;
import com.codeventure.services.impl.AssignmentServiceImpl;
import com.codeventure.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/assignment")
@CrossOrigin("*")
public class AssignmentController {

    @Autowired
    private AssignmentServiceImpl assignmentService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @PostMapping("/")
    public Assignment addAssignment(@RequestBody Assignment assignment){
        return this.assignmentService.addAssignment(assignment);
    }

    @PutMapping("/")
    public Assignment updateAssignment(@RequestBody Assignment assignment){
        return this.assignmentService.updateAssignment(assignment);
    }

    @DeleteMapping("/{id}")
    public void deleteAssignment(@PathVariable("id") long id){
        this.assignmentService.deleteAssignment(id);
    }


//    Getting Assignments
    @GetMapping("/{id}")
    public Assignment getSingleAssignment(@PathVariable("id") long id){
        return this.assignmentService.getSingleAssignment(id);
    }

    @GetMapping("/")
    public List<Assignment> getAllAssignments(){
        return this.assignmentService.getAllAssignments();
    }

    @GetMapping("/lecture/{lId}")
    public Assignment getAssignmentByLecture(@PathVariable("lId") long lId){
        return this.assignmentService.getAssignmentByLecture(lId);
    }

    @GetMapping("/user")
    public List<Assignment> getAssignmentsByUserCourses(Principal principal){
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        return this.assignmentService.getAssignmentsByUserCourses(user);
    }

    @GetMapping("/field/{field}")
    public List<Assignment> getAssignmentsByUserCourses(@PathVariable("field") String  field){
        return this.assignmentService.getAssignmentsByField(field);
    }
}

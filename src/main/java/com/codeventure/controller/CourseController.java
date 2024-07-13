package com.codeventure.controller;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Course;
import com.codeventure.services.impl.CourseServiceImpl;
import com.codeventure.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/course")
@CrossOrigin("*")
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/")
    public Course addCourse(@RequestBody Course course, Principal principal){
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        course.setUser(user);
        return this.courseService.addCourse(course);
    }

    @PutMapping("/")
    public Course updateCourse(@RequestBody Course course, Principal principal){
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        course.setUser(user);
        return this.courseService.updateCourse(course);
    }
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable("id") long id){
        this.courseService.deleteCourse(id);
    }

    @GetMapping("/{id}")
    public Course getSingleCourse(@PathVariable("id") long id){
        return this.courseService.getSingleCourse(id);
    }

    @GetMapping("/")
    public List<Course> getAllCourse(){
        return this.courseService.getAllCourses();
    }

    @GetMapping("/user")
    public List<Course> getCoursesByUser(Principal principal){
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        return this.courseService.getCoursesByUser(user);
    }

    @GetMapping("/user/{id}")
    public Course getSingleCourseUser(@PathVariable("id") long id, Principal principal){
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        return this.courseService.getSingleCourseUser(id,user);
    }

    @GetMapping("/field/{field}")
    public List<Course> getCoursesByField(@PathVariable("field") String field){
        return this.courseService.getCoursesByField(field);
    }
}

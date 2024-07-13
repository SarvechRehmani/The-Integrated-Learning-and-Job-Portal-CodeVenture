package com.codeventure.repo.course;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    public List<Course> findByUser(User user);

    public List<Course> findByField(String field);
}

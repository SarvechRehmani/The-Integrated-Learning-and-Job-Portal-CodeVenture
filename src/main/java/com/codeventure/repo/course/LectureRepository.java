package com.codeventure.repo.course;

import com.codeventure.entities.course.Course;
import com.codeventure.entities.course.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    public List<Lecture> findByCourse(Course course);

    @Query("FROM Lecture L WHERE L.course = :course And L.lNo = :lNo")
    public Lecture findByCourseAndLno(Course course, long lNo);
}

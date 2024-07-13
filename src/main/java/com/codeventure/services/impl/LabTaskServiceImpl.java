package com.codeventure.services.impl;

import com.codeventure.entities.User;
import com.codeventure.entities.course.LabTask;
import com.codeventure.entities.course.Lecture;
import com.codeventure.entities.result.LabTaskResult;
import com.codeventure.helper.DeleteService;
import com.codeventure.repo.UserRepository;
import com.codeventure.repo.course.LabTaskRepository;
import com.codeventure.services.LabTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LabTaskServiceImpl implements LabTaskService {

    @Autowired
    private LabTaskRepository labTaskRepository;

    @Autowired
    private DeleteService deleteService;
    @Autowired
    private LectureServiceImpl lectureService;

    @Override
    public LabTask addLabTask(LabTask labTask) {
        return this.labTaskRepository.save(labTask);
    }

    @Override
    public LabTask updateLabTask(LabTask labTask) {
        LabTask labTask1 = this.getSingleLabTask(labTask.getLabId());
        labTask.setLabTaskResults(labTask1.getLabTaskResults());
        return this.labTaskRepository.save(labTask);
    }

    @Override
    public void deleteLabTask(long id) {
        this.deleteService.deleteLabTask(id);
    }

    @Override
    public LabTask getSingleLabTask(long id) {
        return this.labTaskRepository.findById(id).get();
    }

    @Override
    public List<LabTask> getAllLabTasks() {
        return this.labTaskRepository.findAll();
    }

    @Override
    public LabTask getLabTaskByLecture(long lId) {
        Lecture lecture = this.lectureService.getSingleLecture(lId);
        return this.labTaskRepository.findByLecture(lecture);
    }

    @Override
    public List<LabTask> getLabTaskByUserCourses(User user) {
        List<Lecture> lectures = this.lectureService.getUserCoursesLecture(user);
        List<LabTask> labTasks = new ArrayList<>();
        for (Lecture lecture : lectures) {
            if (lecture.getLabTask() != null) {
                labTasks.add(lecture.getLabTask());
            }
        }
        return labTasks;
    }

    @Override
    public LabTask getSingleLabTaskUser(long id, User user) {
        List<LabTask> labTasks = this.getLabTaskByUserCourses(user);
        for (LabTask labTask : labTasks) {
            if (labTask.getLabId() == id) {
                return labTask;
            }
        }
        return null;
    }

    @Override
    public List<LabTask> getLabTaskByField(String field) {
        List<Lecture> lectures = this.lectureService.getLecturesByField(field);
        List<LabTask> labTasks = new ArrayList<>();
        for (Lecture lecture: lectures){
           if(lecture.getLabTask() != null){
               labTasks.add(lecture.getLabTask());
           }
        }
        return labTasks;
    }
}

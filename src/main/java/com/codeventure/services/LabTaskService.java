package com.codeventure.services;

import com.codeventure.entities.User;
import com.codeventure.entities.course.LabTask;

import java.util.List;

public interface LabTaskService {

    public LabTask addLabTask(LabTask labTask);

    public LabTask updateLabTask(LabTask labTask);

    public void deleteLabTask(long id);

    public LabTask getSingleLabTask(long id);

    public List<LabTask> getAllLabTasks();

    public LabTask getLabTaskByLecture(long lId);

    public List<LabTask> getLabTaskByUserCourses(User user);

    public LabTask getSingleLabTaskUser(long id, User user);
    public List<LabTask> getLabTaskByField(String field);
}

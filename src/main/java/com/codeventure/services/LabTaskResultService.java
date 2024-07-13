package com.codeventure.services;

import com.codeventure.entities.User;
import com.codeventure.entities.course.Assignment;
import com.codeventure.entities.course.LabTask;
import com.codeventure.entities.result.AssignmentResult;
import com.codeventure.entities.result.LabTaskResult;

import java.util.List;

public interface LabTaskResultService {

    public LabTaskResult saveLabTaskResult(LabTaskResult labTaskResult);

    public void deleteLabTaskResult(long lrId);

    public LabTaskResult getSingleLabTaskResultById(long lrId);
    public LabTaskResult getLabTaskResultByLabTaskAndUser(LabTask labTask, User user);

    public LabTaskResult assignMarksLabTaskResult(LabTaskResult labTaskResult);

    public List<LabTaskResult> getAllLabTaskResultByLabTask(LabTask labTask);

    public List<LabTaskResult> getLabTaskResultByUser(User user);



}

package com.codeventure.services.impl;

import com.codeventure.entities.User;
import com.codeventure.entities.course.LabTask;
import com.codeventure.entities.result.AssignmentResult;
import com.codeventure.entities.result.LabTaskResult;
import com.codeventure.helper.DeleteService;
import com.codeventure.repo.UserRepository;
import com.codeventure.repo.course.LabTaskRepository;
import com.codeventure.repo.result.LabTaskResultRepository;
import com.codeventure.services.LabTaskResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabTaskResultServiceImpl implements LabTaskResultService {

    @Autowired
    private LabTaskResultRepository labTaskResultRepository;

    @Autowired
    private DeleteService deleteService;

    @Override
    public LabTaskResult saveLabTaskResult(LabTaskResult labTaskResult) {
        return this.labTaskResultRepository.save(labTaskResult);
    }
    @Override
    public void deleteLabTaskResult(long lrId){
        this.deleteService.deleteLabTaskResult(lrId);
    }

    @Override
    public LabTaskResult getSingleLabTaskResultById(long lrId) {
        return this.labTaskResultRepository.findById(lrId).get();
    }


    @Override
    public LabTaskResult getLabTaskResultByLabTaskAndUser(LabTask labTask, User user) {
        return this.labTaskResultRepository.findByLabTaskAndUser(labTask, user);
    }

    @Override
    public LabTaskResult assignMarksLabTaskResult(LabTaskResult labTaskResult) {
        return this.labTaskResultRepository.save(labTaskResult);
    }

    @Override
    public List<LabTaskResult> getAllLabTaskResultByLabTask(LabTask labTask) {
        return this.labTaskResultRepository.findByLabTask(labTask);
    }

    @Override
    public List<LabTaskResult> getLabTaskResultByUser(User user) {
        return this.labTaskResultRepository.findByUser(user);
    }

}

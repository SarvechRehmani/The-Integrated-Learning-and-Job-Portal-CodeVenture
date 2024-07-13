package com.codeventure.repo.result;

import com.codeventure.entities.User;
import com.codeventure.entities.course.LabTask;
import com.codeventure.entities.result.LabTaskResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabTaskResultRepository extends JpaRepository<LabTaskResult, Long> {

    public LabTaskResult findByLabTaskAndUser(LabTask labTask, User user);

    public List<LabTaskResult> findByLabTask(LabTask labTask);

    public List<LabTaskResult> findByUser(User user);
}
